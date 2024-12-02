package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "disbursement_tickets")

public class DisbursementTicket extends AbstractEntity {

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "bank_account_holder")
    private String bankAccountHolder;

    @Column(name = "loan_amount")
    private String loanAmount;

    @Column(name = "less_fees")
    private String lessFees;

    @Column(name = "application_fee")
    private String applicationFee;

    @Column(name = "cash_handling_fees")
    private String cashHandlingFees;

    @Column(name = "interest_rate")
    private String interestRate;

    @Column(name = "repayment_amount")
    private String repaymentAmount;

    @Column(name = "loan_officer")
    private String loanOfficer;

    @Column(name = "tenor")
    private String tenor;

    @Column(name = "product")
    private String product;

    @Column(name = "collateral")
    private String collateral;

    @Column(name = "status")
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankAccountHolder() {
        return bankAccountHolder;
    }

    public void setBankAccountHolder(String bankAccountHolder) {
        this.bankAccountHolder = bankAccountHolder;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLessFees() {
        return lessFees;
    }

    public void setLessFees(String lessFees) {
        this.lessFees = lessFees;
    }

    public String getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(String applicationFee) {
        this.applicationFee = applicationFee;
    }

    public String getCashHandlingFees() {
        return cashHandlingFees;
    }

    public void setCashHandlingFees(String cashHandlingFees) {
        this.cashHandlingFees = cashHandlingFees;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(String repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public String getLoanOfficer() {
        return loanOfficer;
    }

    public void setLoanOfficer(String loanOfficer) {
        this.loanOfficer = loanOfficer;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCollateral() {
        return collateral;
    }

    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}





