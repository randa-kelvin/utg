package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.LoanRequestMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRequestMicroRepository extends JpaRepository<LoanRequestMicro, String > {
    List<LoanRequestMicro> findAllByLoanId(String id);
}
