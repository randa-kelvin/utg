package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.OperationalCostRequestDTO;
import com.untucapital.usuite.utg.dto.response.OperationalCostResponseDTO;
import com.untucapital.usuite.utg.model.OperationalCost;
import com.untucapital.usuite.utg.repository.OperationalCostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class OperationalCostService {
    @Autowired
    private OperationalCostRepository operationalCostRepository;

    @Transactional(value = "transactionManager")
    public void  saveDirectCost(OperationalCostRequestDTO request) {

        OperationalCost operationalCost = new OperationalCost();
        BeanUtils.copyProperties(request, operationalCost);
        operationalCostRepository.save(operationalCost);
    }

    @Transactional(value = "transactionManager")
    public List<OperationalCostResponseDTO> getAllDirectCosts() {

        List<OperationalCostResponseDTO> response = new ArrayList<OperationalCostResponseDTO>();
        List<OperationalCost> operationalCostList= operationalCostRepository.findAll();

        for (OperationalCost operationalCost : operationalCostList){

            OperationalCostResponseDTO responseDTO = new OperationalCostResponseDTO();
            BeanUtils.copyProperties(operationalCost, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<OperationalCostResponseDTO> getCostsByLoanId(String loanId) {

        List<OperationalCostResponseDTO> response = new ArrayList<OperationalCostResponseDTO>();
        List<OperationalCost> operationalCostList = operationalCostRepository.findbyLoanId(loanId);

        for (OperationalCost operationalCost : operationalCostList){

            OperationalCostResponseDTO responseDTO = new OperationalCostResponseDTO();
            BeanUtils.copyProperties(operationalCost, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteOperationalCost(String id) {
        operationalCostRepository.deleteById(id);
    }

}
