package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="riskAnalysis")
public class RiskAnalysis extends AbstractEntity{
    @NotNull
    @Column(nullable = false, name = "loan_id")
    private String loanId;

    @Column(name = "risk")
    private String risk;

    @Column(name = "details")
    private String details;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
