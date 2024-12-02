package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ApplicationOffundMicro")
public class ApplicationOfFunds extends AbstractEntity {
    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "details")
    private String details;

    @Column(name = "total_investments")
    private String totalInvestments;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTotalInvestments() {
        return totalInvestments;
    }

    public void setTotalInvestments(String totalInvestments) {
        this.totalInvestments = totalInvestments;
    }
}
