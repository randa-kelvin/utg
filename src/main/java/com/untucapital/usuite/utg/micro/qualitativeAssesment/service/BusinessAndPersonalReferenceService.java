package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.BusinessAndPersonalReferenceRepository;
import com.untucapital.usuite.utg.model.BusinessAndPersonalReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessAndPersonalReferenceService {
    @Autowired
    private final BusinessAndPersonalReferenceRepository businessAndPersonalReferenceRepository;

    public BusinessAndPersonalReferenceService(BusinessAndPersonalReferenceRepository businessAndPersonalReferenceRepository) {
        this.businessAndPersonalReferenceRepository = businessAndPersonalReferenceRepository;
    }

    //Add
    public void save(BusinessAndPersonalReference businessAndPersonalReference){
        businessAndPersonalReferenceRepository.save(businessAndPersonalReference);
    }
    //Delete By Id
    public void delete(String id){
        businessAndPersonalReferenceRepository.deleteById(id);
    }
    //Find All By Loan Id
    public List<BusinessAndPersonalReference>  findAllByLoanId(String id){
       return businessAndPersonalReferenceRepository.findAllByLoanId(id);
    }
}
