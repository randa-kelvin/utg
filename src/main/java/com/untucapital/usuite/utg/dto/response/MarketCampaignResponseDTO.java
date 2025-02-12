package com.untucapital.usuite.utg.dto.response;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MarketCampaignResponseDTO extends AbstractEntityDTO {
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


}
