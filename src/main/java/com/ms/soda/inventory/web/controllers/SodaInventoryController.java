package com.ms.soda.inventory.web.controllers;

import com.ms.soda.inventory.web.model.SodaInventoryDto;
import com.ms.soda.inventory.web.services.SodaInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 2019-05-31.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class SodaInventoryController {

    private final SodaInventoryService sodaInventoryService;

    @GetMapping("api/v1/soda/{sodaId}/inventory")
    public List<SodaInventoryDto> listSodasById(@PathVariable UUID sodaId){
        return sodaInventoryService.listSodas(sodaId);
    }
}
