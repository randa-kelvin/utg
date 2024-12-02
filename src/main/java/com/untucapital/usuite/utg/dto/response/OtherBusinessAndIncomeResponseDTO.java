package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class OtherBusinessAndIncomeResponseDTO extends AbstractEntityDTO {

    private String loanId;

    private String description;

    private String since;

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
