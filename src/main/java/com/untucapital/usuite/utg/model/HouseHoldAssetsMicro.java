package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class HouseHoldAssetsMicro extends AbstractEntity {


    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "name")
    private String name;

    @Column(name = "market_value")
    private double marketValue;

    @Column(name = "discount")
    private double discount;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
