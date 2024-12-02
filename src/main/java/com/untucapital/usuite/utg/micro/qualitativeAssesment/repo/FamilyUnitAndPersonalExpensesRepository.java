package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.FamilyUnitAndPersonalExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyUnitAndPersonalExpensesRepository extends JpaRepository<FamilyUnitAndPersonalExpenses, String> {
    List<FamilyUnitAndPersonalExpenses> findAllByLoanId(String id);
}
