package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class MainProductPurchasesMicroRequestDTO extends AbstractEntity {

    private String loanId;

    private String product;

    private double quantity;

    private double frequency;

    private double purchase;

    private double sale;

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


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }
}
