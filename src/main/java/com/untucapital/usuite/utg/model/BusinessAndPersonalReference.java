package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BusinessAndPersonalReferenceMicro")
public class BusinessAndPersonalReference extends AbstractEntity {
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(nullable = false)
    private String relation;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "working_address")
    private String workingAddress;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String comments;


    public BusinessAndPersonalReference() {
    }

    public BusinessAndPersonalReference(String loanId, String relation, String fullName, String workingAddress, String phone, String comments) {
        this.loanId = loanId;
        this.relation = relation;
        this.fullName = fullName;
        this.workingAddress = workingAddress;
        this.phone = phone;
        this.comments = comments;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkingAddress() {
        return workingAddress;
    }

    public void setWorkingAddress(String workingAddress) {
        this.workingAddress = workingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "BusinessAndPersonalReference{" +
                "loanId='" + loanId + '\'' +
                ", relation='" + relation + '\'' +
                ", fullName='" + fullName + '\'' +
                ", workingAddress='" + workingAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
