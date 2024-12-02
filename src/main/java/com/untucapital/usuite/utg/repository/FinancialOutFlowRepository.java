package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.FinancialOutFlow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialOutFlowRepository extends JpaRepository<FinancialOutFlow, String> {
    List<FinancialOutFlow> findFinancialOutFlowByLoanId(String loanId);
}
