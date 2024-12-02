package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.ApplicationOfFunds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationOfFundsRepository extends JpaRepository<ApplicationOfFunds, String> {
    List<ApplicationOfFunds> findAllByLoanId(String id);
}
