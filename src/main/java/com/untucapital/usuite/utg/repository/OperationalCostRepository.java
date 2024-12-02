package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.OperationalCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationalCostRepository extends JpaRepository<OperationalCost, String> {
    @Modifying
    @Query(value = "select * from operational_cost where loan_id = :loanId", nativeQuery = true)
    List<OperationalCost> findbyLoanId(String loanId);
}
