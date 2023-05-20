package com.ms.soda.inventory.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by jt on 2019-05-31.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SodaInventoryDto {
    private UUID id;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private UUID sodaId;
    private Integer quantityOnHand;
    private String upc;
}
