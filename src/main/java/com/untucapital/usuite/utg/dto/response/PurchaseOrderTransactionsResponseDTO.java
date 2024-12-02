package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderTransactionsResponseDTO extends AbstractEntityDTO {
        private String poItem;
        private String poNumber;
        private String poSupplier; // This can still store the ID if you want to keep it
        private String poSupplierName; // New field for storing supplier name
        private String poCategory; // This can still store the ID if you want to keep it
        private String poCategoryName; // New field for storing category name
        private String poQuantity;
        private String poAmount;
        private String poCurrency;
        private String poRequisitionId; // This can still store the ID if you want to keep it
        private String poRequisitionName; // New field for storing requisition name
        private String poStatus;
        private Double zimraTax; // New field for storing ZIMRA tax

        private String poOriginator;
        private String poApprover;
        private String cmsApprover;
}

