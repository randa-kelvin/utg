package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.BusinessUnitRequestDTO;
import com.untucapital.usuite.utg.dto.response.BusinessUnitResponseDTO;
import com.untucapital.usuite.utg.model.BusinessUnit;
import com.untucapital.usuite.utg.repository.BusinessUnitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessUnitService {

    @Autowired
    private final BusinessUnitRepository businessUnitRepository;

    public BusinessUnitService(BusinessUnitRepository businessUnitRepository) {
        this.businessUnitRepository = businessUnitRepository;
    }

    //Add Business Unit
    @Transactional(value = "transactionManager")
    public void saveBusinessUnit(BusinessUnitRequestDTO requestDTO){

        BusinessUnit businessUnit = new BusinessUnit();
        BeanUtils.copyProperties(requestDTO, businessUnit);
        businessUnitRepository.save(businessUnit);
    }

    //Get all Business units
    @Transactional(value = "transactionManager")
    public List<BusinessUnitResponseDTO> listBusinessUnits(){

        List<BusinessUnitResponseDTO> responseDTOList = new ArrayList<>();
        List<BusinessUnit> businessUnitList =  businessUnitRepository.findAll();

        for(BusinessUnit businessUnit : businessUnitList){
            BusinessUnitResponseDTO businessResponseDTO = new BusinessUnitResponseDTO();
            BeanUtils.copyProperties(businessUnit, businessResponseDTO);

            responseDTOList.add(businessResponseDTO);
        }

        return responseDTOList;
    }

    //Get List of Business Units by Loan Id
    @Transactional(value = "transactionManager")
    public List<BusinessUnitResponseDTO> lisBusinessUnitByLoanId(String id){

        List<BusinessUnitResponseDTO> responseDTOList = new ArrayList<>();
        List<BusinessUnit> businessUnitList = businessUnitRepository.findBusinessUnitByLoanId(id);


        for(BusinessUnit businessUnit : businessUnitList){
            BusinessUnitResponseDTO businessResponseDTO = new BusinessUnitResponseDTO();
            BeanUtils.copyProperties(businessUnit, businessResponseDTO);

            responseDTOList.add(businessResponseDTO);
        }

        return responseDTOList;
    }

    //Get List of Business Units by Id
    @Transactional(value = "transactionManager")
    public List<BusinessUnitResponseDTO> lisBusinessUnitByLoanId(String id, String businessUnit){

        List<BusinessUnitResponseDTO> responseDTOList = new ArrayList<>();
        List<BusinessUnit> businessUnitList= businessUnitRepository.findBusinessUnitByLoanId(id);


        for(BusinessUnit businessUnit1 : businessUnitList){
            BusinessUnitResponseDTO businessResponseDTO = new BusinessUnitResponseDTO();
            BeanUtils.copyProperties(businessUnit1, businessResponseDTO);

            responseDTOList.add(businessResponseDTO);
        }

        return responseDTOList;
    }

    //Get Business unit by Id
    @Transactional(value = "transactionManager")
    public List<BusinessUnitResponseDTO> getBusinessUnitById(String id){

        List<BusinessUnitResponseDTO> responseDTOList = new ArrayList<>();
        List<BusinessUnit> businessUnitList = businessUnitRepository.findBusinessUnitById(id);


        for(BusinessUnit businessUnit : businessUnitList){
            BusinessUnitResponseDTO businessResponseDTO = new BusinessUnitResponseDTO();
            BeanUtils.copyProperties(businessUnit, businessResponseDTO);

            responseDTOList.add(businessResponseDTO);
        }

        return responseDTOList;
    }

    //Delete Business Unit by BusinessId
    @Transactional(value = "transactionManager")
    public void deleteBusinessUnit(String id){
        businessUnitRepository.deleteById(id);
    }


}
