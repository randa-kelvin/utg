package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.Guarantor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuarantorRepository extends JpaRepository<Guarantor, String> {
    List<Guarantor> findAllByLoanId(String id);
}
