package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MarketCampaignRequestDTO extends AbstractEntityDTO {

    @JsonProperty("campaignName")
    private String campaignName;

    @JsonProperty("branchName")
    private String branchName;

    @JsonProperty("city")
    private String city;

    @JsonProperty("zoneArea")  // Ensure this matches the exact field name in the JSON
    private String zoneArea;

    @JsonProperty("sector")
    private String sector;

    @JsonProperty("subSector")
    private String subSector;

    @JsonProperty("valueChain")
    private String valueChain;

    @JsonProperty("resourceNeed")
    private String resourceNeed;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("campaignStatus")
    private String campaignStatus;

    @JsonProperty("venue")
    private String venue;

    @JsonProperty("targetAudience")
    private String targetAudience;

    @JsonProperty("objectives")
    private String objectives;

    @JsonProperty("keyPerformanceIndicator")
    private String keyPerformanceIndicator;
}
