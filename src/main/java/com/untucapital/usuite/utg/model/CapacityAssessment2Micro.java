package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CapacityAssessment2Micro extends AbstractEntity {
    @Column(nullable = false, name = "loan_id")
    private String loanId;

    @Column(name = "place_of_employment")
    private String placeOfEmployment;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String reference;

    @Column(name = "net_income")
    private String netIncome;

    @Column(name = "additional_income")
    private String additionalIncome;

    @Column(nullable = false, name = "since")
    private String since;

    @Column(nullable = false, name="expenses")
    private String expenses;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getPlaceOfEmployment() {
        return placeOfEmployment;
    }

    public void setPlaceOfEmployment(String placeOfEmployment) {
        this.placeOfEmployment = placeOfEmployment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(String netIncome) {
        this.netIncome = netIncome;
    }

    public String getAdditionalIncome() {
        return additionalIncome;
    }

    public void setAdditionalIncome(String additionalIncome) {
        this.additionalIncome = additionalIncome;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }
}
