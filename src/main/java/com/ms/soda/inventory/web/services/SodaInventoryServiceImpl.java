package com.ms.soda.inventory.web.services;

import com.ms.soda.inventory.web.mappers.SodaInventoryMapper;
import com.ms.soda.inventory.web.model.SodaInventoryDto;
import com.ms.soda.inventory.repositories.SodaInventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SodaInventoryServiceImpl implements SodaInventoryService {

    private final SodaInventoryRepository sodaInventoryRepository;
    private final SodaInventoryMapper sodaInventoryMapper;

    public SodaInventoryServiceImpl(SodaInventoryRepository sodaInventoryRepository, SodaInventoryMapper sodaInventoryMapper) {
        this.sodaInventoryRepository = sodaInventoryRepository;
        this.sodaInventoryMapper = sodaInventoryMapper;
    }

    @Override
    public List<SodaInventoryDto> listSodas(UUID sodaId) {
        log.debug("Finding Inventory for sodaId:" + sodaId);

        return sodaInventoryRepository.findAllBySodaId(sodaId)
                .stream()
                .map(sodaInventoryMapper::sodaInventoryToSodaInventoryDto)
                .collect(Collectors.toList());
    }
}
