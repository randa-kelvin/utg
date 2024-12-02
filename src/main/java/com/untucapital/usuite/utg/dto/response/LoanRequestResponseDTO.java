package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class LoanRequestResponseDTO extends AbstractEntityDTO {

    private String loanId;
    private String descriptionAndRationalOfInvestment;
    private String quatationAvailability;
    private String whyQuatationNotAvailable;
    private String loanToSupplier;
    private String whyLoanNotToSupplier;
    private double percentageFixedAssets;
    private double percentageWorkingCapital;
    private double clientRequestAmount;
    private String clientRequestMaturity;
    private double clientRequestInterest;
    private double clientRequestInstallments;
    private double proposedLoanAmount;
    private double proposedLoanMaturity;
    private double proposedInterestRate;
    private double proposedLoanInstallment;
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
