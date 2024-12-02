package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.OtherBusinessAndIncomeRepository;
import com.untucapital.usuite.utg.model.OtherBusinessAndIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OtherBusinessAndIncomeService {
    @Autowired
    private final OtherBusinessAndIncomeRepository otherBusinessAndIncomeRepository;

    public OtherBusinessAndIncomeService(OtherBusinessAndIncomeRepository otherBusinessAndIncomeRepository) {
        this.otherBusinessAndIncomeRepository = otherBusinessAndIncomeRepository;
    }
    //Add
    public void save(OtherBusinessAndIncome otherBusinessAndIncome){
        otherBusinessAndIncomeRepository.save(otherBusinessAndIncome);
    }
    //Delete By Id
    public void deleteById(String id){
        otherBusinessAndIncomeRepository.deleteById(id);
    }
    //Find All By Loan Id
    public List<OtherBusinessAndIncome> findAllByLoanId(String id){
        return otherBusinessAndIncomeRepository.findAllByLoanId(id);
    }
}
