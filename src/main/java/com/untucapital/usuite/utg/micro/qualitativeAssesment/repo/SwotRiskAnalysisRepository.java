package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.SwotRiskAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwotRiskAnalysisRepository extends JpaRepository<SwotRiskAnalysis, String> {
    List<SwotRiskAnalysis> findAllByLoanId(String id);
}
