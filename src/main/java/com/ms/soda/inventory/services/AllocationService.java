package com.ms.soda.inventory.services;

import com.ms.soda.model.SodaOrderDto;

public interface AllocationService {

    Boolean allocateOrder(SodaOrderDto sodaOrderDto);

}
