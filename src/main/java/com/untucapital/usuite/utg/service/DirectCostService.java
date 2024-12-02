package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.DirectCostRequestDTO;
import com.untucapital.usuite.utg.dto.response.DirectCostResponseDTO;
import com.untucapital.usuite.utg.model.DirectCost;
import com.untucapital.usuite.utg.repository.DirectCostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectCostService{

    @Autowired
    private final DirectCostRepository directCostRepository;

    public DirectCostService(DirectCostRepository directCostRepository) {
        this.directCostRepository = directCostRepository;
    }

    //Add New Direct Cost
    @Transactional(value = "transactionManager")
    public void addDirectCost(DirectCostRequestDTO request){

        DirectCost directCost = new DirectCost();
        BeanUtils.copyProperties(request,directCost);
        directCostRepository.save(directCost);
    }

    //Find all Direct Costs by Loan id and Sort CreatedAt
    @Transactional(value = "transactionManager")
    public List<DirectCostResponseDTO> allDirectCost(String id){

        List<DirectCostResponseDTO> response = new ArrayList<>();
        List<DirectCost> directCostList = directCostRepository.findDirectCostByLoanId(id);

        for(DirectCost directCost : directCostList){
            DirectCostResponseDTO responseDTO = new DirectCostResponseDTO();
            BeanUtils.copyProperties(directCost, response);

            response.add(responseDTO);
        }

        return response;
    }

    //Delete Direct Cost By id
    @Transactional(value = "transactionManager")
    public void deleteDirectCost(String id){
        directCostRepository.deleteById(id);
    }
}
