package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class InterestAndFinancialCostResponseDTO extends AbstractEntityDTO {

    private String loanId;

    private String interestAndFinancialCost;

    private String taxes;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getInterestAndFinancialCost() {
        return interestAndFinancialCost;
    }

    public void setInterestAndFinancialCost(String interestAndFinancialCost) {
        this.interestAndFinancialCost = interestAndFinancialCost;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }
}
