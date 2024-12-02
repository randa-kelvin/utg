package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "riskMitigation")
public class MitigatingRisk extends  AbstractEntity{
    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "mitigation")
    private String mitigation;

    @Column(name = "impact")
    private String impact;

    @Column(name = "likelihood")
    private String likelihood;

    @Column(name = "priority")
    private String priority;

    @Column(name = "comment")
    private String comment;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getMitigation() {
        return mitigation;
    }

    public void setMitigation(String mitigation) {
        this.mitigation = mitigation;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(String likelihood) {
        this.likelihood = likelihood;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
