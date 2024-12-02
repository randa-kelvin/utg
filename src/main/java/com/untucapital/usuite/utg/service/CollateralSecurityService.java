package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.CollateralSecurityRequestDTO;
import com.untucapital.usuite.utg.dto.response.CollateralSecurityResponseDTO;
import com.untucapital.usuite.utg.model.CollateralSecurity;
import com.untucapital.usuite.utg.repository.CollateraiSecurityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CollateralSecurityService {

    @Autowired
    CollateraiSecurityRepository collateraiSecurityRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<CollateralSecurityResponseDTO> get(String loanId){

        List<CollateralSecurityResponseDTO> response = new ArrayList<CollateralSecurityResponseDTO>();
        List<CollateralSecurity> collateralSecuritiesList = collateraiSecurityRepository.findCollateralSecurityByLoanId(loanId);

        for(CollateralSecurity collateralSecurity: collateralSecuritiesList){
            CollateralSecurityResponseDTO responseDTO = new CollateralSecurityResponseDTO();
            BeanUtils.copyProperties(collateralSecurity, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void delete(String id){

        collateraiSecurityRepository.deleteById(id);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void add(CollateralSecurityRequestDTO request){

        CollateralSecurity collateralSecurity = new CollateralSecurity();
        BeanUtils.copyProperties(request, collateralSecurity);
        collateraiSecurityRepository.save(collateralSecurity);
    }


}
