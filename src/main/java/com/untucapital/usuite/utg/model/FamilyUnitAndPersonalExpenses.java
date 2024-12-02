package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FamilyUnitAndPersonalExpensesMicro")
public class FamilyUnitAndPersonalExpenses extends AbstractEntity {
    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "name_of_spouse")
    private String nameOfSpouse;

    @Column(name = "guarantor")
    private String guarantor;

    @Column(name = "number_of_children")
    private String numberOfChildren;

    @Column(name = "other_dependents")
    private String otherDependents;

    public FamilyUnitAndPersonalExpenses() {
    }

    public FamilyUnitAndPersonalExpenses(String loanId, String maritalStatus, String nameOfSpouse, String guarantor, String numberOfChildren, String otherDependents) {
        this.loanId = loanId;
        this.maritalStatus = maritalStatus;
        this.nameOfSpouse = nameOfSpouse;
        this.guarantor = guarantor;
        this.numberOfChildren = numberOfChildren;
        this.otherDependents = otherDependents;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNameOfSpouse() {
        return nameOfSpouse;
    }

    public void setNameOfSpouse(String nameOfSpouse) {
        this.nameOfSpouse = nameOfSpouse;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getOtherDependents() {
        return otherDependents;
    }

    public void setOtherDependents(String otherDependents) {
        this.otherDependents = otherDependents;
    }

    @Override
    public String toString() {
        return "FamilyUnitAndPersonalExpenses{" +
                "loanId='" + loanId + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", nameOfSpouse='" + nameOfSpouse + '\'' +
                ", guarantor='" + guarantor + '\'' +
                ", numberOfChildren='" + numberOfChildren + '\'' +
                ", otherDependents='" + otherDependents + '\'' +
                '}';
    }
}
