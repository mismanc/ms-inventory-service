package com.ms.soda.inventory.web.services;

import com.ms.soda.model.SodaInventoryDto;

import java.util.List;
import java.util.UUID;

public interface SodaInventoryService {
    List<SodaInventoryDto> listSodas(UUID sodaId);

}
