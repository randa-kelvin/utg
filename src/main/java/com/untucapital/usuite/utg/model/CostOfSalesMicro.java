package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CostOfSalesMicro extends AbstractEntity {

    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "value")
    private double value;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
