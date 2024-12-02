package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class RepaymentHistoryRequestDTO extends AbstractEntityDTO {


    private String loanId;

    private String lendingInstitution;

    private double amount;

    private String dateOfDisb;

    private String maturity;

    private String outstandingInstallments;

    private double totalDaysInArrears;

    private double averageDaysInArrears;

    private String classification;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLendingInstitution() {
        return lendingInstitution;
    }

    public void setLendingInstitution(String lendingInstitution) {
        this.lendingInstitution = lendingInstitution;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateOfDisb() {
        return dateOfDisb;
    }

    public void setDateOfDisb(String dateOfDisb) {
        this.dateOfDisb = dateOfDisb;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getOutstandingInstallments() {
        return outstandingInstallments;
    }

    public void setOutstandingInstallments(String outstandingInstallments) {
        this.outstandingInstallments = outstandingInstallments;
    }

    public double getTotalDaysInArrears() {
        return totalDaysInArrears;
    }

    public void setTotalDaysInArrears(double totalDaysInArrears) {
        this.totalDaysInArrears = totalDaysInArrears;
    }

    public double getAverageDaysInArrears() {
        return averageDaysInArrears;
    }

    public void setAverageDaysInArrears(double averageDaysInArrears) {
        this.averageDaysInArrears = averageDaysInArrears;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
