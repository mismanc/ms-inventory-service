package com.ms.soda.inventory.services;

import com.ms.soda.events.NewInventoryEvent;
import com.ms.soda.inventory.config.JMSConfig;
import com.ms.soda.inventory.domain.SodaInventory;
import com.ms.soda.inventory.repositories.SodaInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class NewInventoryListener {

    private final SodaInventoryRepository sodaInventoryRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent newInventoryEvent) {
        log.debug("Got Inventory : " + newInventoryEvent.toString());
        sodaInventoryRepository.save(SodaInventory.builder().
                sodaId(newInventoryEvent.getSodaDto().getId())
                .quantityOnHand(newInventoryEvent.getSodaDto().getQuantityOnHand())
                .upc(String.valueOf(newInventoryEvent.getSodaDto().getUpc())).build());
    }


}
