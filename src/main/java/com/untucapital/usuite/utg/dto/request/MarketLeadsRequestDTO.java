package com.untucapital.usuite.utg.dto.request;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class MarketLeadsRequestDTO extends AbstractEntityDTO {

    private String campaignID;
    private String loanOfficer;
    private String branchName;
    private String location;
    private String clientName;
    private String natureOfBusiness;
    private String contactNumber;
    private String contactEmail;
    private String businessAddress;
    private String potentialLoanAmount;
    private String interactionComments;
    private String followUpStatus;

    public String getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(String campaignID) {
        this.campaignID = campaignID;
    }

    public String getLoanOfficer() {
        return loanOfficer;
    }

    public void setLoanOfficer(String loanOfficer) {
        this.loanOfficer = loanOfficer;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getPotentialLoanAmount() {
        return potentialLoanAmount;
    }

    public void setPotentialLoanAmount(String potentialLoanAmount) {
        this.potentialLoanAmount = potentialLoanAmount;
    }

    public String getInteractionComments() {
        return interactionComments;
    }

    public void setInteractionComments(String interactionComments) {
        this.interactionComments = interactionComments;
    }
    public String getFollowUpStatus() {
        return followUpStatus;
    }

    public void setFollowUpStatus(String followUpStatus) {
        this.followUpStatus = followUpStatus;
    }
}
