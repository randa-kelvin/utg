package com.untucapital.usuite.utg.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "MarketLeads")
public class MarketLeads extends AbstractEntity {

    @Column(name = "campaign_id")
    private String campaignID;

    @Column(name = "loan_officer")
    private String loanOfficer;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "location")
    private String location;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "nature_of_business")
    private String natureOfBusiness;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "business_address")
    private String businessAddress;

    @Column(name = "potential_loan_amount")
    private String potentialLoanAmount;

    @Column(name = "interaction_comments")
    private String interactionComments;

    @Column(name = "follow_up_status")
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
