package com.untucapital.usuite.utg.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "purchaseOrderTransactions")
public class PurchaseOrderTransactions extends AbstractEntity{

    @Column(name = "po_item", nullable = false)
    @NotNull
    private String poItem;

    @Column(name = "po_supplier")
    @NotNull
    private String poSupplier;

    @Column(name = "po_category")
    @NotNull
    private String poCategory;

    @Column(name = "po_quantity")
    @NotNull
    private String poQuantity;

    @Column(name = "po_amount")
    @NotNull
    private String poAmount;

    @Column(name = "po_currency")
    @NotNull
    private String poCurrency;

    @Column(name = "po_requisition_id", nullable = false)
    @NotNull
    private String poRequisitionId;

    @Column(name = "po_status")
    @NotNull
    private String poStatus;

}
