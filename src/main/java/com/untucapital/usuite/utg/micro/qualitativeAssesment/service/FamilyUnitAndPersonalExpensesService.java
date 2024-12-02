package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.FamilyUnitAndPersonalExpensesRepository;
import com.untucapital.usuite.utg.model.FamilyUnitAndPersonalExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyUnitAndPersonalExpensesService {
    @Autowired
    private final FamilyUnitAndPersonalExpensesRepository familyUnitAndPersonalExpensesRepository;

    public FamilyUnitAndPersonalExpensesService(FamilyUnitAndPersonalExpensesRepository familyUnitAndPersonalExpensesRepository) {
        this.familyUnitAndPersonalExpensesRepository = familyUnitAndPersonalExpensesRepository;
    }
    //Add
    public void save(FamilyUnitAndPersonalExpenses familyUnitAndPersonalExpenses){
        familyUnitAndPersonalExpensesRepository.save(familyUnitAndPersonalExpenses);
    }
    //Delete By Id
    public void deleteById(String id){
        familyUnitAndPersonalExpensesRepository.deleteById(id);
    }
    //Find All By loanId
    public List<FamilyUnitAndPersonalExpenses> findAllByLoanId(String id){
        return familyUnitAndPersonalExpensesRepository.findAllByLoanId(id);
    }
}
