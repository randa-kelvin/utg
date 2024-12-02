package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.BalanceSheetRepository;
import com.untucapital.usuite.utg.model.BalanceSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceSheetService {
    @Autowired
    private final BalanceSheetRepository balanceSheetRepository;

    public BalanceSheetService(BalanceSheetRepository balanceSheetRepository) {
        this.balanceSheetRepository = balanceSheetRepository;
    }
    //Add
    public void save(BalanceSheet balanceSheet){
        balanceSheetRepository.save(balanceSheet);
    }
    //Delete by id
    public void deleteByLoanId(String id){
        balanceSheetRepository.deleteById(id);
    }
    //Find all by loan id
    public List<BalanceSheet> findAllByLoanId(String id){
        return balanceSheetRepository.findAllByLoanId(id);
    }
}

