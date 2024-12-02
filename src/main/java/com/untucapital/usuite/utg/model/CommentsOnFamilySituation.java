package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CommentsOnFamilySituationMicro")
public class CommentsOnFamilySituation extends AbstractEntity {

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "description")
    private String description;
    public CommentsOnFamilySituation() {
    }

    public CommentsOnFamilySituation(String loanId, String description) {
        this.loanId = loanId;
        this.description = description;
    }

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

    @Override
    public String toString() {
        return "CommentsOnFamilySituation{" +
                "loanId='" + loanId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
