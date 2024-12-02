package com.untucapital.usuite.utg.pos.service;

import com.untucapital.usuite.utg.dto.response.BudgetResponseDTO;
import com.untucapital.usuite.utg.pos.dto.req.BudgetRequestDTO;
import com.untucapital.usuite.utg.pos.model.Budget;
import com.untucapital.usuite.utg.pos.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Service
@RequiredArgsConstructor
public class BudgetService {
    public final BudgetRepository budgetRepository;
    public final POSCategoryService posCategoryService;

    //1. create budget
    @Transactional(value = "transactionManager")
    public BudgetResponseDTO createBudget(BudgetRequestDTO request) {
        Budget budget = new Budget();
        BeanUtils.copyProperties(request, budget);

        Budget savedBudget = budgetRepository.save(budget);

        BudgetResponseDTO response = new BudgetResponseDTO();
        BeanUtils.copyProperties(savedBudget, response);

        return response;
    }

    //2. get budget by id
    @Transactional(value = "transactionManager")
    public BudgetResponseDTO getBudgetById(String id) {

        BudgetResponseDTO response = new BudgetResponseDTO();
        Budget budget = budgetRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(budget, response);
        return response;
    }

    //3. get all budgets
    @Transactional(value = "transactionManager")
    public List<BudgetResponseDTO> getAllBudgets() {

        List<BudgetResponseDTO> response = new ArrayList<>();
        List<Budget> budgetList = budgetRepository.findAll();

        for (Budget budget : budgetList) {
            BudgetResponseDTO budgetResponse = new BudgetResponseDTO();
            String categoryName = posCategoryService.getCategoryById(Integer.valueOf(budget.getCategory())).getName();
            BeanUtils.copyProperties(budget, budgetResponse);
            budgetResponse.setCategory(categoryName);
            response.add(budgetResponse);
        }
        return response;
    }

    //4. update budget
    @Transactional(value = "transactionManager")
    public BudgetResponseDTO updateBudget(BudgetRequestDTO budget) {
        Budget existingBudget = budgetRepository.findById(budget.getId())
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        existingBudget.setCategory(budget.getCategory());
        existingBudget.setYear(budget.getYear());
        existingBudget.setAmount(budget.getAmount());
        existingBudget.setJanuary(budget.getJanuary());
        existingBudget.setFebruary(budget.getFebruary());
        existingBudget.setMarch(budget.getMarch());
        existingBudget.setApril(budget.getApril());
        existingBudget.setMay(budget.getMay());
        existingBudget.setJune(budget.getJune());
        existingBudget.setJuly(budget.getJuly());
        existingBudget.setAugust(budget.getAugust());
        existingBudget.setSeptember(budget.getSeptember());
        existingBudget.setOctober(budget.getOctober());
        existingBudget.setNovember(budget.getNovember());
        existingBudget.setDecember(budget.getDecember());

        Budget updatedBudget = budgetRepository.save(existingBudget);

        BudgetResponseDTO response = new BudgetResponseDTO();
        BeanUtils.copyProperties(updatedBudget, response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<Budget> getBudgetByYear(Integer year) {

        BudgetResponseDTO budgetResponse = new BudgetResponseDTO();
        List<Budget> budget = budgetRepository.findByYear(year);

        return budget;
    }

    //5. delete budget
    @Transactional(value = "transactionManager")
    public BudgetResponseDTO deleteBudget(String id) {

        BudgetResponseDTO budgetResponse = new BudgetResponseDTO();
        Budget budget = budgetRepository.findById(id).orElse(null);
        budgetRepository.deleteById(id);
        BeanUtils.copyProperties(budget, budgetResponse);
        return budgetResponse;
    }
}
