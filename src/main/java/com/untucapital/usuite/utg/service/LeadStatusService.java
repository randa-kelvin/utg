package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.dto.request.LeadStatusRequestDTO;
import com.untucapital.usuite.utg.dto.response.LeadStatusResponseDTO;
import com.untucapital.usuite.utg.model.LeadStatus;
import com.untucapital.usuite.utg.repository.LeadStatusRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class LeadStatusService {

    @Autowired
    private LeadStatusRepository leadStatusRepository;

    @Transactional(value = "transactionManager")
    public List<LeadStatusResponseDTO> getAllZones() {

        List<LeadStatusResponseDTO> response = new ArrayList<>();
        List<LeadStatus> leadStatusList = leadStatusRepository.findAll();

        for(LeadStatus leadStatus: leadStatusList){
            LeadStatusResponseDTO responseDTO = new LeadStatusResponseDTO();
            BeanUtils.copyProperties(leadStatus, response);

           response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<LeadStatusResponseDTO> getZoneById(String id) {

        List<LeadStatusResponseDTO> response = new ArrayList<LeadStatusResponseDTO>();
        List<LeadStatus> leadStatusList= (List<LeadStatus>) leadStatusRepository.findLeadStatusById(id);

        for(LeadStatus leadStatus: leadStatusList){
            LeadStatusResponseDTO responseDTO = new LeadStatusResponseDTO();
            BeanUtils.copyProperties(leadStatus, response);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void saveZones(LeadStatusRequestDTO request) {

        LeadStatus leadStatus = new LeadStatus();
        BeanUtils.copyProperties(request,leadStatus);
        leadStatusRepository.save(leadStatus);
    }

    @Transactional(value = "transactionManager")
    public void deleteZones(String id) {
        leadStatusRepository.deleteById(id);
    }

}

