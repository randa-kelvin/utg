package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "longTermCreditHistory")
public class LongTermCreditHistory extends AbstractEntity{
    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "institution_name")
    private String institutionName;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "maturity")
    private String maturity;

    @Column(name = "date_of_disb")
    private String dateOfDisb;

    @Column(name = "installments")
    private String installments;

    @Column(name = "outstanding_bal")
    private String outstandingBal;

    @Column(name = "days_in_arrears")
    private String daysInArrears;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getDateOfDisb() {
        return dateOfDisb;
    }

    public void setDateOfDisb(String dateOfDisb) {
        this.dateOfDisb = dateOfDisb;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public String getOutstandingBal() {
        return outstandingBal;
    }

    public void setOutstandingBal(String outstandingBal) {
        this.outstandingBal = outstandingBal;
    }

    public String getDaysInArrears() {
        return daysInArrears;
    }

    public void setDaysInArrears(String daysInArrears) {
        this.daysInArrears = daysInArrears;
    }
}
