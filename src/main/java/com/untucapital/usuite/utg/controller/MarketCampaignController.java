package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.MarketCampaignRequestDTO;
import com.untucapital.usuite.utg.dto.response.MarketCampaignResponseDTO;
import com.untucapital.usuite.utg.service.MarketCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market_campaigns")
public class MarketCampaignController {
    private final MarketCampaignService marketCampaignService;

    @Autowired
    public MarketCampaignController(MarketCampaignService marketCampaignService) {
        this.marketCampaignService = marketCampaignService;
    }

    @GetMapping
    public List<MarketCampaignResponseDTO> getAllMarketCampaigns() {
        return marketCampaignService.getAllMarketCampaigns();
    }

    @GetMapping("/{id}")
    public MarketCampaignResponseDTO getMarketCampaignById(@PathVariable String id) {
        return marketCampaignService.getMarketCampaignById(id);
    }
    @GetMapping("/campaignStatus/{campaignStatus}")
    public List<MarketCampaignResponseDTO> getMarketCampaignByStatus(@PathVariable String campaignStatus) {
        return marketCampaignService.getMarketCampaignByStatus(campaignStatus);
    }

    @PostMapping
    public MarketCampaignResponseDTO createMarketCampaign(@RequestBody MarketCampaignRequestDTO marketCampaign) {
        return marketCampaignService.createMarketCampaign(marketCampaign);
    }

    @PutMapping("/{id}")
    public MarketCampaignResponseDTO updateMarketCampaign(
            @PathVariable String id,
            @RequestBody MarketCampaignRequestDTO marketCampaign
    ) {
        return marketCampaignService.updateMarketCampaign(id, marketCampaign);
    }

    @PutMapping("campaignStatus/{id}")
    public MarketCampaignResponseDTO updateMarketCampaignStatus(
            @PathVariable String id,
            @RequestBody MarketCampaignRequestDTO marketCampaign
    ) {
        return marketCampaignService.updateMarketCampaignStatus(id, marketCampaign);
    }

    @DeleteMapping("/{id}")
    public void deleteMarketCampaign(@PathVariable String id) {
        marketCampaignService.deleteMarketCampaign(id);
    }
}
