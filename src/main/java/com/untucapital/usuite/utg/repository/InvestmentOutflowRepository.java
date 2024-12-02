package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.InvestmentOutflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentOutflowRepository extends JpaRepository<InvestmentOutflow, String > {
    List<InvestmentOutflow> findInvestmentOutflowByLoanId(String loanId);
}
