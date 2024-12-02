package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.GeneralBusinessInformationRepository;
import com.untucapital.usuite.utg.model.GeneralBusinessInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralBusinessInformationService {
    @Autowired
    private final GeneralBusinessInformationRepository generalBusinessInformationRepository;

    public GeneralBusinessInformationService(GeneralBusinessInformationRepository generalBusinessInformationRepository) {
        this.generalBusinessInformationRepository = generalBusinessInformationRepository;
    }
    //Add
    public void saveGeneral(GeneralBusinessInformation generalBusinessInformation){
        generalBusinessInformationRepository.save(generalBusinessInformation);
    }
    //Delete
    public void deleteGeneral(String id){
        generalBusinessInformationRepository.deleteById(id);
    }
    //Get By Loan Id
    public List<GeneralBusinessInformation> findAllByLoanId(String id){
        return generalBusinessInformationRepository.findAllByLoanId(id);
    }
}
