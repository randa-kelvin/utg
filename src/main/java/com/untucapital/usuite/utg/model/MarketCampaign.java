package com.untucapital.usuite.utg.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MarketCampaign")
public class MarketCampaign extends AbstractEntity {
//    @Id
    @Column(name = "campaign_id")
    private Long campaignID;

    @Column(name = "campaign_name")
    private String campaignName;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "city")
    private String city;

    @Column(name = "zone_area")
    private String zoneArea;

    @Column(name = "sector")
    private String sector;

    @Column(name = "sub_sector")
    private String subSector;

    @Column(name = "value_chain")
    private String valueChain;

    @Column(name = "resource_need")
    private String resourceNeed;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "allocated_loan_officer")
    private String AllocatedLoanOfficer;

    @Column(name = "campaign_status")
    private String campaignStatus;

    @Column(name = "venue")
    private String venue;

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "objectives")
    private String objectives;

    @Column(name = "key_performance_indicator")
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
