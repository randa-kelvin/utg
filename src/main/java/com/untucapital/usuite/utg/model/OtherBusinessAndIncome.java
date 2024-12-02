package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OtherBusinessAndIncomeMicro")
public class OtherBusinessAndIncome extends AbstractEntity {

    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "description")
    private String description;

    @Column(name = "since")
    private String since;

    @Column(name = "monthly_net_income")
    private double monthlyNetIncome;


    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public double getMonthlyNetIncome() {
        return monthlyNetIncome;
    }

    public void setMonthlyNetIncome(double monthlyNetIncome) {
        this.monthlyNetIncome = monthlyNetIncome;
    }
}
