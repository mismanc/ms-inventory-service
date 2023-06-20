package com.ms.soda.inventory.web.mappers;

import com.ms.soda.inventory.domain.SodaInventory;
import com.ms.soda.model.SodaInventoryDto;
import org.mapstruct.Mapper;

/**
 * Created by jt on 2019-05-31.
 */
@Mapper(uses = {DateMapper.class})
public interface SodaInventoryMapper {

    SodaInventory sodaInventoryDtoToSodaInventory(SodaInventoryDto sodaInventoryDTO);

    SodaInventoryDto sodaInventoryToSodaInventoryDto(SodaInventory sodaInventory);
}
