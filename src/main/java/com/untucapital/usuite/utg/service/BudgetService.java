//package com.untucapital.usuite.utg.service;
//
//import com.untucapital.usuite.utg.model.Budget;
//import com.untucapital.usuite.utg.repository.BudgetRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.Year;
//import java.util.List;
//
///**
// * @author tjchidanika
// * @created 5/9/2023
// */
//
//@Service
//@RequiredArgsConstructor
//public class BudgetService {
//    public final BudgetRepository budgetRepository;
//    //1. create budget
//    @Transactional(value = "transactionManager")
//    public Budget createBudget(Budget budget) {
//        return budgetRepository.save(budget);
//    }
//    //2. get budget by id
//    @Transactional(value = "transactionManager")
//    public Budget getBudgetById(Integer id) {
//        return budgetRepository.findById(id).orElse(null);
//    }
//    //3. get all budgets
//    @Transactional(value = "transactionManager")
//    public List<Budget> getAllBudgets() {
//        return budgetRepository.findAll();
//    }
//    //4. update budget
//    @Transactional(value = "transactionManager")
//    public Budget updateBudget(Budget budget) {
//        Budget existingBudget = budgetRepository.findById(budget.getId()).orElse(null);
//        assert existingBudget != null;
//        existingBudget.setCategory(budget.getCategory());
//        existingBudget.setYear(budget.getYear());
//        existingBudget.setJanuary(budget.getJanuary());
//        existingBudget.setFebruary(budget.getFebruary());
//        existingBudget.setMarch(budget.getMarch());
//        existingBudget.setApril(budget.getApril());
//        existingBudget.setMay(budget.getMay());
//        existingBudget.setJune(budget.getJune());
//        existingBudget.setJuly(budget.getJuly());
//        existingBudget.setAugust(budget.getAugust());
//        existingBudget.setSeptember(budget.getSeptember());
//        existingBudget.setOctober(budget.getOctober());
//        existingBudget.setNovember(budget.getNovember());
//        existingBudget.setDecember(budget.getDecember());
//        return budgetRepository.save(existingBudget);
//    }
//    //5. delete budget
//    @Transactional(value = "transactionManager")
//    public Budget deleteBudget(Integer id) {
//        Budget budget = budgetRepository.findById(id).orElse(null);
//        budgetRepository.deleteById(id);
//        return budget;
//    }
//}
