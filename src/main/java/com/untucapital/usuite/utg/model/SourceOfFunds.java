package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sourceOfFunds")
public class SourceOfFunds extends AbstractEntity{
    @NotNull
    @Column(nullable = false, name = "loan_id")
    private String loanId;
    @Column(name = "source")
    private String source;

    @Column(name = "source_amount")
    private double sourceAmount;

    @Column(name = "percentage_share")
    private String percentageShare;

    @Column(name = "verified")
    private String verified;

    @Column(name = "comments")
    private String comments;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public String getPercentageShare() {
        return percentageShare;
    }

    public void setPercentageShare(String percentageShare) {
        this.percentageShare = percentageShare;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
