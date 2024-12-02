package com.untucapital.usuite.utg.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "FollowUpDiary")
public class FollowUpDiary extends AbstractEntity  {

    @Column(name = "client_id")
    private String clientID;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_contact_number")
    private String clientContactNumber;

    @Column(name = "client_contact_email")
    private String clientContactEmail;

    @Column(name = "client_business_address")
    private String clientBusinessAddress;

    @Column(name = "follow_up_comments")
    private String followUpComments;

    @Column(name = "contacted")
    private String contacted;

    @Column(name = "follow_up_status")
    private String followUpStatus;

    @Column(name = "loan_officer_name")
    private String loanOfficerName;


    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientContactNumber() {
        return clientContactNumber;
    }

    public void setClientContactNumber(String clientContactNumber) {
        this.clientContactNumber = clientContactNumber;
    }

    public String getClientContactEmail() {
        return clientContactEmail;
    }

    public void setClientContactEmail(String clientContactEmail) {
        this.clientContactEmail = clientContactEmail;
    }

    public String getClientBusinessAddress() {
        return clientBusinessAddress;
    }

    public void setClientBusinessAddress(String clientBusinessAddress) {
        this.clientBusinessAddress = clientBusinessAddress;
    }

    public String getFollowUpComments() {
        return followUpComments;
    }

    public void setFollowUpComments(String followUpComments) {
        this.followUpComments = followUpComments;
    }

    public String getContacted() {
        return contacted;
    }

    public void setContacted(String contacted) {
        this.contacted = contacted;
    }

    public String getFollowUpStatus() {
        return followUpStatus;
    }

    public void setFollowUpStatus(String followUpStatus) {
        this.followUpStatus = followUpStatus;
    }

    public String getLoanOfficerName() {
        return loanOfficerName;
    }

    public void setLoanOfficerName(String loanOfficerName) {
        this.loanOfficerName = loanOfficerName;
    }
}
