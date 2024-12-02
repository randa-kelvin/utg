package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "loanRequest")
public class LoanRequest extends AbstractEntity{

    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "description_and_rational_of_investment")
    private String descriptionAndRationalOfInvestment;

    @Column(name = "quatation_availability")
    private String quatationAvailability;

    @Column(name = "why_quatation_not_available")
    private String whyQuatationNotAvailable;

    @Column(name = "loan_to_supplier")
    private String loanToSupplier;

    @Column(name = "why_loan_not_to_supplier")
    private String whyLoanNotToSupplier;

    @Column(name = "percentage_fixed_assets")
    private double percentageFixedAssets;

    @Column(name = "percentage_working_capital")
    private double percentageWorkingCapital;

    @Column(name = "client_request_amount")
    private double clientRequestAmount;

    @Column(name = "client_request_maturity")
    private String clientRequestMaturity;

    @Column(name = "client_request_interest")
    private double clientRequestInterest;

    @Column(name = "client_request_installments")
    private double clientRequestInstallments;

    @Column(name = "proposed_loan_amount")
    private double proposedLoanAmount;

    @Column(name = "proposed_loan_maturity")
    private double proposedLoanMaturity;

    @Column(name = "proposed_interest_rate")
    private double proposedInterestRate;

    @Column(name = "proposed_loan_installment")
    private double proposedLoanInstallment;

    @Column(name = "comments_terms_and_conditions")
    private String commentsTermsAndConditions;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDescriptionAndRationalOfInvestment() {
        return descriptionAndRationalOfInvestment;
    }

    public void setDescriptionAndRationalOfInvestment(String descriptionAndRationalOfInvestment) {
        this.descriptionAndRationalOfInvestment = descriptionAndRationalOfInvestment;
    }

    public String getQuatationAvailability() {
        return quatationAvailability;
    }

    public void setQuatationAvailability(String quatationAvailability) {
        this.quatationAvailability = quatationAvailability;
    }

    public String getWhyQuatationNotAvailable() {
        return whyQuatationNotAvailable;
    }

    public void setWhyQuatationNotAvailable(String whyQuatationNotAvailable) {
        this.whyQuatationNotAvailable = whyQuatationNotAvailable;
    }

    public String getLoanToSupplier() {
        return loanToSupplier;
    }

    public void setLoanToSupplier(String loanToSupplier) {
        this.loanToSupplier = loanToSupplier;
    }

    public String getWhyLoanNotToSupplier() {
        return whyLoanNotToSupplier;
    }

    public void setWhyLoanNotToSupplier(String whyLoanNotToSupplier) {
        this.whyLoanNotToSupplier = whyLoanNotToSupplier;
    }

    public double getPercentageFixedAssets() {
        return percentageFixedAssets;
    }

    public void setPercentageFixedAssets(double percentageFixedAssets) {
        this.percentageFixedAssets = percentageFixedAssets;
    }

    public double getPercentageWorkingCapital() {
        return percentageWorkingCapital;
    }

    public void setPercentageWorkingCapital(double percentageWorkingCapital) {
        this.percentageWorkingCapital = percentageWorkingCapital;
    }

    public double getClientRequestAmount() {
        return clientRequestAmount;
    }

    public void setClientRequestAmount(double clientRequestAmount) {
        this.clientRequestAmount = clientRequestAmount;
    }

    public String getClientRequestMaturity() {
        return clientRequestMaturity;
    }

    public void setClientRequestMaturity(String clientRequestMaturity) {
        this.clientRequestMaturity = clientRequestMaturity;
    }

    public double getClientRequestInterest() {
        return clientRequestInterest;
    }

    public void setClientRequestInterest(double clientRequestInterest) {
        this.clientRequestInterest = clientRequestInterest;
    }

    public double getClientRequestInstallments() {
        return clientRequestInstallments;
    }

    public void setClientRequestInstallments(double clientRequestInstallments) {
        this.clientRequestInstallments = clientRequestInstallments;
    }

    public String getCommentsTermsAndConditions() {
        return commentsTermsAndConditions;
    }

    public void setCommentsTermsAndConditions(String commentsTermsAndConditions) {
        this.commentsTermsAndConditions = commentsTermsAndConditions;
    }

    public double getProposedLoanAmount() {
        return proposedLoanAmount;
    }

    public void setProposedLoanAmount(double proposedLoanAmount) {
        this.proposedLoanAmount = proposedLoanAmount;
    }

    public double getProposedLoanMaturity() {
        return proposedLoanMaturity;
    }

    public void setProposedLoanMaturity(double proposedLoanMaturity) {
        this.proposedLoanMaturity = proposedLoanMaturity;
    }

    public double getProposedInterestRate() {
        return proposedInterestRate;
    }

    public void setProposedInterestRate(double proposedInterestRate) {
        this.proposedInterestRate = proposedInterestRate;
    }

    public double getProposedLoanInstallment() {
        return proposedLoanInstallment;
    }

    public void setProposedLoanInstallment(double proposedLoanInstallment) {
        this.proposedLoanInstallment = proposedLoanInstallment;
    }
}
