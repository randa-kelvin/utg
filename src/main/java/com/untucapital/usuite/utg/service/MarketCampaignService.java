package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.MarketCampaignRequestDTO;
import com.untucapital.usuite.utg.dto.response.MarketCampaignResponseDTO;
import com.untucapital.usuite.utg.model.MarketCampaign;
import com.untucapital.usuite.utg.repository.MarketCampaignRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarketCampaignService {
    private final MarketCampaignRepository marketCampaignRepository;

    @Autowired
    public MarketCampaignService(MarketCampaignRepository marketCampaignRepository) {
        this.marketCampaignRepository = marketCampaignRepository;
    }

    @Transactional(value = "transactionManager")
    public List<MarketCampaignResponseDTO> getAllMarketCampaigns() {

        List<MarketCampaignResponseDTO> response = new ArrayList<MarketCampaignResponseDTO>();
        List<MarketCampaign> marketCampaignList= marketCampaignRepository.findAll();

        for(MarketCampaign marketCampaign: marketCampaignList){
            MarketCampaignResponseDTO responseDTO = new MarketCampaignResponseDTO();
            BeanUtils.copyProperties(marketCampaign, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @Transactional(value = "transactionManager")
    public MarketCampaignResponseDTO getMarketCampaignById(String id) {

        MarketCampaignResponseDTO response = new MarketCampaignResponseDTO();
        MarketCampaign marketCampaign= marketCampaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid  ID"));
        BeanUtils.copyProperties(marketCampaign,response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public List< MarketCampaignResponseDTO> getMarketCampaignByStatus(String campaignStatus) {

        List<MarketCampaignResponseDTO> response = new ArrayList<MarketCampaignResponseDTO>();
        List<MarketCampaign> marketCampaignList= marketCampaignRepository.findMarketCampaignByCampaignStatus(campaignStatus);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid  campaignStatus"));

        for(MarketCampaign marketCampaign: marketCampaignList){
            MarketCampaignResponseDTO responseDTO = new MarketCampaignResponseDTO();
            BeanUtils.copyProperties(marketCampaign, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @Transactional(value = "transactionManager")
    public MarketCampaignResponseDTO createMarketCampaign(MarketCampaignRequestDTO request) {

        MarketCampaignResponseDTO response = new MarketCampaignResponseDTO();
        MarketCampaign marketCampaign = new MarketCampaign();
        BeanUtils.copyProperties(request, marketCampaign);
        MarketCampaign marketCampaign1 = marketCampaignRepository.save(marketCampaign);
        BeanUtils.copyProperties(marketCampaign1,response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public MarketCampaignResponseDTO updateMarketCampaign(String id, MarketCampaignRequestDTO marketCampaign) {

        MarketCampaignResponseDTO response = new MarketCampaignResponseDTO();
        MarketCampaign existingCampaign = marketCampaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid  ID"));

        existingCampaign.setCampaignName(marketCampaign.getCampaignName());
        existingCampaign.setBranchName(marketCampaign.getBranchName());
        existingCampaign.setCity(marketCampaign.getCity());
        // Set other fields as needed

        existingCampaign.setCampaignStatus(marketCampaign.getCampaignStatus());
        MarketCampaign marketCampaign1= marketCampaignRepository.save(existingCampaign);
        BeanUtils.copyProperties(marketCampaign1,response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public MarketCampaignResponseDTO updateMarketCampaignStatus(String id, MarketCampaignRequestDTO marketCampaign) {

        MarketCampaignResponseDTO response = new MarketCampaignResponseDTO();
        Optional<MarketCampaign> optionalMarketCampaign = marketCampaignRepository.findById(id);
        if (optionalMarketCampaign.isPresent()) {
            MarketCampaign existingMarketCampaign = optionalMarketCampaign.get();

            existingMarketCampaign.setCampaignStatus(marketCampaign.getCampaignStatus());
            MarketCampaign marketCampaign1= marketCampaignRepository.save(existingMarketCampaign);
            BeanUtils.copyProperties(marketCampaign1, response);
        } else {
            return null;
        }
        return response;
    }


    @Transactional(value = "transactionManager")
    public void deleteMarketCampaign(String id) {
        marketCampaignRepository.deleteById(id);
    }
}
