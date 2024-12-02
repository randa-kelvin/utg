package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.BusinessAndPersonalReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessAndPersonalReferenceRepository extends JpaRepository<BusinessAndPersonalReference, String>{
    List<BusinessAndPersonalReference> findAllByLoanId(String id);
}
