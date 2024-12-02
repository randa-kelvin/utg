package com.untucapital.usuite.utg.pos.processor;

import com.untucapital.usuite.utg.pos.dto.ExpenditureResponseDto;
import com.untucapital.usuite.utg.pos.dto.POSBalanceSheetDto;
import com.untucapital.usuite.utg.pos.model.Budget;
import com.untucapital.usuite.utg.pos.model.Expenditure;
import com.untucapital.usuite.utg.pos.model.POSBalanceSheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author panashe rutimhu
 * @created 20/11/2023
 */

@Component
@Slf4j
public class ExpenditureProcessor {


    public List<ExpenditureResponseDto> setResponse(List<Expenditure> expenditureList){

        List<ExpenditureResponseDto> result = new ArrayList<>();

        Float total = 0f;

        for (Expenditure expenditure : expenditureList) {

            ExpenditureResponseDto response = new ExpenditureResponseDto();
            response.setCategory(expenditure.getCategory());
            response.setMonth(expenditure.getMonth());
            response.setYear(expenditure.getYear());
            response.setAmount(expenditure.getAmount());
            total = total + Float.parseFloat(String.valueOf(expenditure.getAmount()));
            response.setTotalAmount(total);

            result.add(response);
        }

        return result;
    }

    private static final String[] MONTHS = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    public List<POSBalanceSheetDto> setBalanceSheetResponse(List<Budget> budgetList, List<Object[]> expenditureDtoList, int year) {
        List<POSBalanceSheetDto> posBalanceSheetList = new ArrayList<>();

        // Create a map of budgets by month for quick lookup
        Map<Integer, Budget> budgetMap = budgetList.stream()
                .collect(Collectors.toMap(Budget::getMonth, budget -> budget));

        for (Object[] result : expenditureDtoList) {
            POSBalanceSheetDto posBalanceSheetDto = new POSBalanceSheetDto();
            POSBalanceSheet posBalanceSheet = new POSBalanceSheet();

            String monthString = (String) result[0];
            int month = Integer.parseInt(monthString);
            BigDecimal sumAmountBig = (BigDecimal) result[1];
            float sumAmount = sumAmountBig.floatValue();

            Budget budget = budgetMap.get(month);
            if (budget != null) {
                posBalanceSheet.setExpenditure(sumAmount);
                posBalanceSheet.setBudget(budget.getAmount());
                posBalanceSheet.setBalance(budget.getAmount() - sumAmount);
                posBalanceSheet.setMonth(MONTHS[month - 1]);
            } else {
                throw new RuntimeException("Budget not found for month: " + month);
            }

            posBalanceSheet.setYear(year);
            BeanUtils.copyProperties(posBalanceSheet, posBalanceSheetDto);
            posBalanceSheetList.add(posBalanceSheetDto);
            log.info("PosBalanceSheetList: {}", posBalanceSheetList);
        }

        return posBalanceSheetList;
    }
}
