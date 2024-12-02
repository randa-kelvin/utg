package com.untucapital.usuite.utg.pos.dto.res;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;

public class PastPurchasesMicroResponseDTO extends AbstractEntityDTO {


    private String loanId;

    private String date;

    private String supplier;

    private String product;

    private String amount;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
