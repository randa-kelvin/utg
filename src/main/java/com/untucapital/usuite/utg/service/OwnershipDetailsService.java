package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.OwnershipDetailsRequestDTO;
import com.untucapital.usuite.utg.dto.response.OwnershipDetailsResponseDTO;
import com.untucapital.usuite.utg.model.OwnershipDetails;
import com.untucapital.usuite.utg.repository.OwnerShipDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class OwnershipDetailsService {
    @Autowired
    OwnerShipDetailsRepository ownerShipDetailsRepository;

    @Transactional(value = "transactionManager")
    public void saveOwenershipDetails(OwnershipDetailsRequestDTO request){

        OwnershipDetails ownershipDetails = new OwnershipDetails();
        BeanUtils.copyProperties(request, ownershipDetails);
        ownerShipDetailsRepository.save(ownershipDetails);
    }

    @Transactional(value = "transactionManager")
    public List<OwnershipDetailsResponseDTO> getOwnershipDetailsByLoanId(String loanId){

        List<OwnershipDetailsResponseDTO> response = new ArrayList<OwnershipDetailsResponseDTO>();
        List<OwnershipDetails> ownershipDetailsList= ownerShipDetailsRepository.findByLoanId(loanId);

        for (OwnershipDetails ownershipDetails : ownershipDetailsList) {
            OwnershipDetailsResponseDTO responseDTO = new OwnershipDetailsResponseDTO();
            BeanUtils.copyProperties(ownershipDetails, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteOwnershipDetails(String id){
        ownerShipDetailsRepository.deleteById(id);
    }

}
