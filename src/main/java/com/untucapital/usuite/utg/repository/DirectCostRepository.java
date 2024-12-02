package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.DirectCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectCostRepository extends JpaRepository <DirectCost, String> {

    //Find Business Unit By Loan id
    List<DirectCost> findDirectCostByLoanId(String loanId);

}
