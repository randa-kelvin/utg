package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.BusinessRequestDTO;
import com.untucapital.usuite.utg.dto.response.BusinessResponseDTO;
import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.repository.BusinessRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@javax.transaction.Transactional
public class BusinessService {
    @Autowired
    private BusinessRepository businessRepository;

    @Transactional(value = "transactionManager")
    public List<BusinessResponseDTO> listAllBusiness() {

        List<BusinessResponseDTO> response = new ArrayList<>();
        List<Business> businessList = businessRepository.findAll();

        for(Business business: businessList){
            BusinessResponseDTO businessResponseDTO = new BusinessResponseDTO();
            BeanUtils.copyProperties(business, businessResponseDTO);

            response.add(businessResponseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void saveBusiness(BusinessRequestDTO request) {

        Business business = new Business();
        BeanUtils.copyProperties(request, business);
        businessRepository.save(business);
    }

    @Transactional(value = "transactionManager")
    public BusinessResponseDTO getBusiness(Integer id) {

        BusinessResponseDTO response = new BusinessResponseDTO();
        Business business = businessRepository.findById(id).get();
        BeanUtils.copyProperties(business, response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteBusiness(Integer id) {
        businessRepository.deleteById(id);
    }
}
