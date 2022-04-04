package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.OperationalExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationalExpensesRepository extends JpaRepository<OperationalExpenses, String > {
    List<OperationalExpenses> findAllByLoanId(String id);
}
