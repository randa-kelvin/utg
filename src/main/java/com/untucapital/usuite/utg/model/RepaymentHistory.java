package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RepaymentHistoryMicro")
public class RepaymentHistory extends AbstractEntity {

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "lending_institution")
    private String lendingInstitution;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date_of_disb")
    private String dateOfDisb;

    @Column(name = "maturity")
    private String maturity;

    @Column(name = "outstanding_installments")
    private String outstandingInstallments;

    @Column(name = "total_days_in_arrears")
    private double totalDaysInArrears;

    @Column(name = "average_days_in_arrears")
    private double averageDaysInArrears;

    @Column(name = "classification")
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
