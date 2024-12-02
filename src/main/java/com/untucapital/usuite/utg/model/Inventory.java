package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "InventoryMicro")
public class Inventory extends AbstractEntity {
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "purchase_price")
    private String purchasePrice;

    @Column(name = "sale_price")
    private String salePrice;

    @Column(name = "stock_count_date")
    private String stockCountDate;


    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getStockCountDate() {
        return stockCountDate;
    }

    public void setStockCountDate(String stockCountDate) {
        this.stockCountDate = stockCountDate;
    }

}
