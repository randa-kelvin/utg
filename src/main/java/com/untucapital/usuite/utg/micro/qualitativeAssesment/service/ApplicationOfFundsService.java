package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.ApplicationOfFundsRepository;
import com.untucapital.usuite.utg.model.ApplicationOfFunds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationOfFundsService {
    @Autowired
    private final ApplicationOfFundsRepository applicationOfFundsRepository;

    public ApplicationOfFundsService(ApplicationOfFundsRepository applicationOfFundsRepository) {
        this.applicationOfFundsRepository = applicationOfFundsRepository;
    }
    //Add
    public void save(ApplicationOfFunds applicationOfFunds){
        applicationOfFundsRepository.save(applicationOfFunds);
    }
    //Delete by id
    public void deleteById(String id){
        applicationOfFundsRepository.deleteById(id);
    }
    //Find all by loan id
    public List<ApplicationOfFunds> findAllByLoanId(String id){
        return applicationOfFundsRepository.findAllByLoanId(id);
    }
}
