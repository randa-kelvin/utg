package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GuarantorMicro")
public class Guarantor extends AbstractEntity {

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "activity")
    private String activity;

    @Column(name = "income")
    private String income;

    @Column(name = "relationship")
    private String relationship;

    public Guarantor() {
    }

    public Guarantor(String loanId, String fullName, String activity, String income, String relationship) {
        this.loanId = loanId;
        this.fullName = fullName;
        this.activity = activity;
        this.income = income;
        this.relationship = relationship;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "Guarantor{" +
                "loanId='" + loanId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", activity='" + activity + '\'' +
                ", income='" + income + '\'' +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}
