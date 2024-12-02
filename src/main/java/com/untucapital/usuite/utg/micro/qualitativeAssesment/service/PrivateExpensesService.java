package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.PrivateExpensesRepository;
import com.untucapital.usuite.utg.model.PrivateExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PrivateExpensesService {
    @Autowired
    private final PrivateExpensesRepository privateExpensesRepository;

    public PrivateExpensesService(PrivateExpensesRepository privateExpensesRepository) {
        this.privateExpensesRepository = privateExpensesRepository;
    }
    //Add
    public void save(@RequestBody PrivateExpenses privateExpenses){
        privateExpensesRepository.save(privateExpenses);
    }
    //Delete By Id
    public void deleteById(String id){
        privateExpensesRepository.deleteById(id);
    }
    //Find All By Loan Id
    public List<PrivateExpenses> findAllByLoanId(String id){
        return privateExpensesRepository.findAllByLoanId(id);
    }

}
