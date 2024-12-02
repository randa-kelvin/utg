package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CreditHistoryResponseDTO extends AbstractEntityDTO {

    private String loanId;
    private String institutionName;
    private String loanType;
    private double loanAmount;
    private String maturity;
    private String dateOfDisb;
    private String installments;
    private String outstandingBal;
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
