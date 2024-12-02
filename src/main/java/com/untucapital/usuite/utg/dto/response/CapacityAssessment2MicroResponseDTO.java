package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CapacityAssessment2MicroResponseDTO extends AbstractEntityDTO {

    private String loanId;
    private String placeOfEmployment;
    private String position;
    private String reference;
    private String netIncome;
    private String additionalIncome;
    private String since;
    private String expenses;

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public void setPlaceOfEmployment(String placeOfEmployment) {
        this.placeOfEmployment = placeOfEmployment;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setNetIncome(String netIncome) {
        this.netIncome = netIncome;
    }

    public void setAdditionalIncome(String additionalIncome) {
        this.additionalIncome = additionalIncome;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }
}
