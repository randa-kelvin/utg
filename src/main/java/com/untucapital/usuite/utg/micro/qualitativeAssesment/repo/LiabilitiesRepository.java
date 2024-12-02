package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.Liabilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiabilitiesRepository extends JpaRepository<Liabilities, String> {
    List<Liabilities> findAllByLoanId(String id);
}
