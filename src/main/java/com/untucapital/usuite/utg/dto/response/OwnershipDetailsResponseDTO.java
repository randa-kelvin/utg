package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class OwnershipDetailsResponseDTO extends AbstractEntityDTO {

    private String loanId;

    private String fullName;

    private String position;

    private String experience;

    private String percentageShare;

    private String married;

    private String dob;

    private String sygnatoryType;

    private String crbCheck;

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
