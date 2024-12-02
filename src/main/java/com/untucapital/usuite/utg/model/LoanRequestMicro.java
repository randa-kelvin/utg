package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LoanRequestMicro extends AbstractEntity {
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "client_installments")
    private double clientInstallments;

    @Column(name = "client_interest_rate")
    private double clientInterestRate;

    @Column(name = "propose_amount")
    private double proposeAmount;

    @Column(name = "proposed_maturity")
    private String proposedMaturity;

    @Column(name = "proposed_installments")
    private double proposedInstallments;

    @Column(name = "proposed_interest_rate")
    private double proposedInterestRate;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public double getClientInstallments() {
        return clientInstallments;
    }

    public void setClientInstallments(double clientInstallments) {
        this.clientInstallments = clientInstallments;
    }

    public double getClientInterestRate() {
        return clientInterestRate;
    }

    public void setClientInterestRate(double clientInterestRate) {
        this.clientInterestRate = clientInterestRate;
    }

    public double getProposeAmount() {
        return proposeAmount;
    }

    public void setProposeAmount(double proposeAmount) {
        this.proposeAmount = proposeAmount;
    }

    public String getProposedMaturity() {
        return proposedMaturity;
    }

    public void setProposedMaturity(String proposedMaturity) {
        this.proposedMaturity = proposedMaturity;
    }

    public double getProposedInstallments() {
        return proposedInstallments;
    }

    public void setProposedInstallments(double proposedInstallments) {
        this.proposedInstallments = proposedInstallments;
    }

    public double getProposedInterestRate() {
        return proposedInterestRate;
    }

    public void setProposedInterestRate(double proposedInterestRate) {
        this.proposedInterestRate = proposedInterestRate;
    }
}
