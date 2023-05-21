package com.ms.soda.inventory.bootstrap;

import com.ms.soda.inventory.domain.SodaInventory;
import com.ms.soda.inventory.repositories.SodaInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
// @Component
@Slf4j
@RequiredArgsConstructor
public class SodaInventoryBootstrap implements CommandLineRunner {
    public static final String SODA_1_UPC = "0631234200036";
    public static final String SODA_2_UPC = "0631234300019";
    public static final String SODA_3_UPC = "0083783375213";
    public static final UUID SODA_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID SODA_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID SODA_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final SodaInventoryRepository sodaInventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if(sodaInventoryRepository.count() == 0){
            loadInitialInv();
        }
    }

    private void loadInitialInv() {
        sodaInventoryRepository.save(SodaInventory
                .builder()
                .sodaId(SODA_1_UUID)
                .upc(SODA_1_UPC)
                .quantityOnHand(50)
                .build());

        sodaInventoryRepository.save(SodaInventory
                .builder()
                .sodaId(SODA_2_UUID)
                .upc(SODA_2_UPC)
                .quantityOnHand(50)
                .build());

        sodaInventoryRepository.saveAndFlush(SodaInventory
                .builder()
                .sodaId(SODA_3_UUID)
                .upc(SODA_3_UPC)
                .quantityOnHand(50)
                .build());

        log.debug("Loaded Inventory. Record count: " + sodaInventoryRepository.count());
    }
}
