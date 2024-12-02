package com.untucapital.usuite.utg.dto.request;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;

public class MarketCampaignRequestDTO extends AbstractEntityDTO {
//    @Id
private Long campaignID;
    private String campaignName;
    private String branchName;
    private String city;
    private String zoneArea;
    private String sector;
    private String subSector;
    private String valueChain;
    private String resourceNeed;
    private String startDate;
    private String endDate;
    private String AllocatedLoanOfficer;
    private String campaignStatus;
    private String venue;
    private String targetAudience;
    private String objectives;
    private String keyPerformanceIndicator;
    public Long getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(Long campaignID) {
        this.campaignID = campaignID;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZoneArea() {
        return zoneArea;
    }

    public void setZoneArea(String zoneArea) {
        this.zoneArea = zoneArea;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSubSector() {
        return subSector;
    }

    public void setSubSector(String subSector) {
        this.subSector = subSector;
    }

    public String getValueChain() {
        return valueChain;
    }

    public void setValueChain(String valueChain) {
        this.valueChain = valueChain;
    }

    public String getResourceNeed() {
        return resourceNeed;
    }

    public void setResourceNeed(String resourceNeed) {
        this.resourceNeed = resourceNeed;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAllocatedLoanOfficer() {
        return AllocatedLoanOfficer;
    }

    public void setAllocatedLoanOfficer(String allocatedLoanOfficer) {
        AllocatedLoanOfficer = allocatedLoanOfficer;
    }

    public String getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getKeyPerformanceIndicator() {
        return keyPerformanceIndicator;
    }

    public void setKeyPerformanceIndicator(String keyPerformanceIndicator) {
        this.keyPerformanceIndicator = keyPerformanceIndicator;
    }
}
