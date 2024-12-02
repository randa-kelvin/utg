package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.RepaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentHistoryRepository extends JpaRepository<RepaymentHistory, String> {
    List<RepaymentHistory> findAllByLoanId(String id);
}
