package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "collateralSecurity")
public class CollateralSecurity extends AbstractEntity {

    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "ownership")
    private String ownership;

    @Column(name = "market_value")
    private String marketValue;

    @Column(name = "discount_value")
    private String discountValue;


    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }
}
