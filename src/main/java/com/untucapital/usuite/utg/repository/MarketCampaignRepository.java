package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.MarketCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketCampaignRepository extends JpaRepository<MarketCampaign, String> {

    List<MarketCampaign> findMarketCampaignByCampaignStatus(String campaignStatus);
}
