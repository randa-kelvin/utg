package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.MarketLeadsRequestDTO;
import com.untucapital.usuite.utg.dto.response.MarketLeadsResponseDTO;
import com.untucapital.usuite.utg.model.MarketLeads;
import com.untucapital.usuite.utg.repository.MarketLeadsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarketLeadsService {

    private final MarketLeadsRepository marketLeadsRepository;

    @Autowired
    public MarketLeadsService(MarketLeadsRepository marketLeadsRepository) {
        this.marketLeadsRepository = marketLeadsRepository;
    }

    @Transactional(value = "transactionManager")
    public MarketLeadsResponseDTO createMarketLeads(MarketLeadsRequestDTO request) {

        MarketLeads marketLeads = new MarketLeads();
        MarketLeadsResponseDTO response = new MarketLeadsResponseDTO();

        BeanUtils.copyProperties(request, marketLeads);
        MarketLeads marketLeads1 = marketLeadsRepository.save(marketLeads);
        BeanUtils.copyProperties(marketLeads1, response);

        return response;

    }

    @Transactional(value = "transactionManager")
    public MarketLeadsResponseDTO updateMarketLeads(String id, MarketLeadsRequestDTO marketLeads) {

        MarketLeadsResponseDTO response = new MarketLeadsResponseDTO();

        Optional<MarketLeads> optionalMarketLeads = marketLeadsRepository.findById(id);
        if (optionalMarketLeads.isPresent()) {
            MarketLeads existingMarketLeads = optionalMarketLeads.get();
            existingMarketLeads.setLoanOfficer(marketLeads.getLoanOfficer());
            existingMarketLeads.setBranchName(marketLeads.getBranchName());
            existingMarketLeads.setClientName(marketLeads.getClientName());
            existingMarketLeads.setNatureOfBusiness(marketLeads.getNatureOfBusiness());
            existingMarketLeads.setContactNumber(marketLeads.getContactNumber());
            existingMarketLeads.setContactEmail(marketLeads.getContactEmail());
            existingMarketLeads.setBusinessAddress(marketLeads.getBusinessAddress());
            existingMarketLeads.setPotentialLoanAmount(marketLeads.getPotentialLoanAmount());
            existingMarketLeads.setInteractionComments(marketLeads.getInteractionComments());
            existingMarketLeads.setFollowUpStatus(marketLeads.getFollowUpStatus());

            MarketLeads marketLeads1= marketLeadsRepository.save(existingMarketLeads);
            BeanUtils.copyProperties(marketLeads1,response);

            return response;
        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public MarketLeadsResponseDTO updateMarketLeadsStatus(String id, MarketLeadsRequestDTO marketLeads) {

        MarketLeadsResponseDTO response = new MarketLeadsResponseDTO();
        Optional<MarketLeads> optionalMarketLeads = marketLeadsRepository.findById(id);
        if (optionalMarketLeads.isPresent()) {
            MarketLeads existingMarketLeads = optionalMarketLeads.get();

            existingMarketLeads.setFollowUpStatus(marketLeads.getFollowUpStatus());
            MarketLeads marketLeads1= marketLeadsRepository.save(existingMarketLeads);
            BeanUtils.copyProperties(marketLeads1, response);

            return response;
        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public MarketLeadsResponseDTO getMarketLeadsById(String id) {

        MarketLeadsResponseDTO result = new MarketLeadsResponseDTO();
        Optional<MarketLeads> optionalMarketLeads = marketLeadsRepository.findById(id);

        if (optionalMarketLeads.isPresent()) {
            MarketLeads marketLeads = optionalMarketLeads.get();
            BeanUtils.copyProperties(marketLeads,result);

            return result;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public List<MarketLeadsResponseDTO> getAllMarketLeads() {

        List<MarketLeadsResponseDTO> result = new ArrayList<>();
        List<MarketLeads> marketLeadsList= marketLeadsRepository.findAll();

        for(MarketLeads marketLeads: marketLeadsList){
            MarketLeadsResponseDTO response = new MarketLeadsResponseDTO();
            BeanUtils.copyProperties(marketLeads,response);

            result.add(response);
        }

        return result;
    }

    @Transactional(value = "transactionManager")
    public List<MarketLeadsResponseDTO> getMarketLeadsByBranch( String branch) {

        List<MarketLeadsResponseDTO> response = new ArrayList<MarketLeadsResponseDTO>();
        List<MarketLeads> marketLeadsList = marketLeadsRepository.findMarketLeadsByBranchName(branch);

        for(MarketLeads marketLeads : marketLeadsList){
            MarketLeadsResponseDTO responseDTO = new MarketLeadsResponseDTO();
            BeanUtils.copyProperties(marketLeads, responseDTO);

            response.add(responseDTO);
        }

        return response;

    }

    @Transactional(value = "transactionManager")
    public List<MarketLeadsResponseDTO> getMarketLeadsByLoanOfficer(String loanOfficer) {

        List<MarketLeadsResponseDTO> response = new ArrayList<>();
        List<MarketLeads> marketLeadsList = marketLeadsRepository.findMarketLeadsByLoanOfficer(loanOfficer);

        for(MarketLeads marketLeads : marketLeadsList){
            MarketLeadsResponseDTO responseDTO = new MarketLeadsResponseDTO();
            BeanUtils.copyProperties(marketLeads, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteMarketLeads(String id) {
        marketLeadsRepository.deleteById(id);
    }
}
