package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BusinessAssetsMicro extends AbstractEntity {

    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(nullable = false)
    private String name;

    @Column(name = "market_value")
    private String marketValue;

    @Column(nullable = false)
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

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
