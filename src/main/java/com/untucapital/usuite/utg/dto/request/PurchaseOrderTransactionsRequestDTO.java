package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderTransactionsRequestDTO extends AbstractEntityDTO {


    private String poItem;

    private String poSupplier;

    private String poCategory;

    private String poQuantity;

    private String poAmount;

    private String poCurrency;

    private String poRequisitionId;

    private String poStatus;
}
