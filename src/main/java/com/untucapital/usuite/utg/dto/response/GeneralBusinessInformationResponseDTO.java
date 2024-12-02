package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;

/**
 * @author Takunda Chidanika HpProbook - 24/03/2022
 */

public class GeneralBusinessInformationResponseDTO extends AbstractEntityDTO {


    private String loanId;

    private String client;

    private String name;

    private String visitDate;

    private String activity;

    private String mainLocation;

    private String locationOfOtherPos;

    private Long numberOfWorkers;

    private String clientName;

    private String legalStatus;

    private String activitySince;

    private String businessPremises;

    private String numberOfWorkingDays;

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
