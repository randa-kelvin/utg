package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ownershipDetails")
public class OwnershipDetails extends AbstractEntity {

    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "position")
    private String position;

    @Column(name = "experience")
    private String experience;

    @Column(name = "percentage_share")
    private String percentageShare;

    @Column(name = "married")
    private String married;

    @Column(name = "dob")
    private String dob;

    @Column(name = "sygnatory_type")
    private String sygnatoryType;

    @Column(name = "crb_check")
    private String crbCheck;

    @Column(name = "contact")
    private String contact;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPercentageShare() {
        return percentageShare;
    }

    public void setPercentageShare(String percentageShare) {
        this.percentageShare = percentageShare;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSygnatoryType() {
        return sygnatoryType;
    }

    public void setSygnatoryType(String sygnatoryType) {
        this.sygnatoryType = sygnatoryType;
    }

    public String getCrbCheck() {
        return crbCheck;
    }

    public void setCrbCheck(String crbCheck) {
        this.crbCheck = crbCheck;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
