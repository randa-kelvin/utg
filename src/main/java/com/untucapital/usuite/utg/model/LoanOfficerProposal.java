package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LoanOfficerPro")
public class LoanOfficerProposal extends AbstractEntity {

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "maturity")
    private String maturity;

    @Column(name = "instalments")
    private double instalments;

    @Column(name = "interest_rate")
    private double interestRate;
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public double getInstalments() {
        return instalments;
    }

    public void setInstalments(double instalments) {
        this.instalments = instalments;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
