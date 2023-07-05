package com.ms.soda.inventory.services;

import com.ms.soda.inventory.domain.SodaInventory;
import com.ms.soda.inventory.repositories.SodaInventoryRepository;
import com.ms.soda.model.SodaOrderDto;
import com.ms.soda.model.SodaOrderLineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class AllocationServiceImpl implements AllocationService {

    private final SodaInventoryRepository inventoryRepository;

    @Override
    public Boolean allocateOrder(SodaOrderDto sodaOrderDto) {
        log.debug("Allocating OrderId: " + sodaOrderDto.getId());

        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        sodaOrderDto.getSodaOrderLines().forEach(line -> {
            int orderQuantity = line.getOrderQuantity() != null ? line.getOrderQuantity() : 0;
            int allocateQuantity = line.getQuantityAllocated() != null ? line.getQuantityAllocated() : 0;
            int difference = orderQuantity - allocateQuantity;
            if (difference > 0) {
                allocateOrderLine(line);
            }
            totalOrdered.set(totalOrdered.get() + orderQuantity);
            totalAllocated.set(totalAllocated.get() + allocateQuantity);
        });

        log.debug("Total Ordered: " + totalOrdered.get() + " Total Allocated: " + totalAllocated.get());
        return totalOrdered.get() == totalAllocated.get();
    }

    @Override
    public void deallocateOrder(SodaOrderDto sodaOrderDto) {
        sodaOrderDto.getSodaOrderLines().forEach(line->{
            SodaInventory inventory = SodaInventory.builder()
                    .sodaId(line.getSodaId())
                    .upc(line.getUpc())
                    .quantityOnHand(line.getQuantityAllocated())
                    .build();
            SodaInventory sodaInventory = inventoryRepository.save(inventory);
            log.debug("Saved Inventory for soda upc: " + sodaInventory.getUpc() + " inventory id : " + sodaInventory.getId());
        });
    }

    public void allocateOrderLine(SodaOrderLineDto line) {
        List<SodaInventory> inventoryList = inventoryRepository.findByUpc(line.getUpc());

        int orderQty = line.getOrderQuantity() != null ? line.getOrderQuantity() : 0;
        int allocateQty = line.getOrderQuantity() != null ? line.getQuantityAllocated() : 0;
        int qtyToAllocate = orderQty - allocateQty;

        inventoryList.forEach(inv -> {
            int inventory = inv.getQuantityOnHand() != null ? inv.getQuantityOnHand() : 0;
            if (inventory >= qtyToAllocate) {
                inventory = inventory - qtyToAllocate;
                line.setQuantityAllocated(orderQty);
                inv.setQuantityOnHand(inventory);
                inventoryRepository.save(inv);
            } else if (inventory > 0) {
                line.setQuantityAllocated(allocateQty + inventory);
                inv.setQuantityOnHand(0);
                inventoryRepository.delete(inv);
            }
        });
    }
}
