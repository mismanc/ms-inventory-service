package com.ms.soda.inventory.repositories;

import com.ms.soda.inventory.domain.SodaInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SodaInventoryRepository extends JpaRepository<SodaInventory, UUID> {
    List<SodaInventory> findAllBySodaId(UUID sodaId);
}
