package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.LongTermCreditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LongTermCreditHistoryRepository extends JpaRepository<LongTermCreditHistory, String> {
    List<LongTermCreditHistory> findByLoanId(String loanId);
}
