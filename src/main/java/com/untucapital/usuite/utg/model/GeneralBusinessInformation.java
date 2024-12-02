package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Takunda Chidanika HpProbook - 24/03/2022
 */

@Entity
@Table( name = "GeneralBusinessInformationMicro")
public class GeneralBusinessInformation extends AbstractEntity {

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "client")
    private String client;

    @Column(name = "name")
    private String name;

    @Column(name = "visit_date")
    private String visitDate;

    @Column(name = "activity")
    private String activity;

    @Column(name = "main_location")
    private String mainLocation;

    @Column(name = "location_of_other_pos")
    private String locationOfOtherPos;

    @Column(name = "number_of_workers")
    private Long numberOfWorkers;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "legal_status")
    private String legalStatus;

    @Column(name = "activity_since")
    private String activitySince;

    @Column(name = "business_premises")
    private String businessPremises;

    @Column(name = "number_of_working_days")
    private String numberOfWorkingDays;

    @Column(name = "business_history_and_organisation")
    private String businessHistoryAndOrganisation;
//    public GeneralBusinessInformation() {
//    }
//
//    public GeneralBusinessInformation(String loanId, String client, String name, String activity, String mainLocation, String locationOfOtherPos, Long numberOfWorkers, String clientName, String legalStatus, String activitySince, String businessPremises, String numberOfWorkingDays, String businessHistoryAndOrganisation) {
//        this.loanId = loanId;
//        this.client = client;
//        this.name = name;
//        this.activity = activity;
//        this.mainLocation = mainLocation;
//        this.locationOfOtherPos = locationOfOtherPos;
//        this.numberOfWorkers = numberOfWorkers;
//        this.clientName = clientName;
//        this.legalStatus = legalStatus;
//        this.activitySince = activitySince;
//        this.businessPremises = businessPremises;
//        this.numberOfWorkingDays = numberOfWorkingDays;
//        this.businessHistoryAndOrganisation = businessHistoryAndOrganisation;
//    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getMainLocation() {
        return mainLocation;
    }

    public void setMainLocation(String mainLocation) {
        this.mainLocation = mainLocation;
    }

    public String getLocationOfOtherPos() {
        return locationOfOtherPos;
    }

    public void setLocationOfOtherPos(String locationOfOtherPos) {
        this.locationOfOtherPos = locationOfOtherPos;
    }

    public Long getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(Long numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getActivitySince() {
        return activitySince;
    }

    public void setActivitySince(String activitySince) {
        this.activitySince = activitySince;
    }

    public String getBusinessPremises() {
        return businessPremises;
    }

    public void setBusinessPremises(String businessPremises) {
        this.businessPremises = businessPremises;
    }

    public String getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public void setNumberOfWorkingDays(String numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    public String getBusinessHistoryAndOrganisation() {
        return businessHistoryAndOrganisation;
    }

    public void setBusinessHistoryAndOrganisation(String businessHistoryAndOrganisation) {
        this.businessHistoryAndOrganisation = businessHistoryAndOrganisation;
    }


}
