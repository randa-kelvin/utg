package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.GuarantorAssessmentDetailsMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuarantorAssessmentDetailsMicroRepository extends JpaRepository<GuarantorAssessmentDetailsMicro, String> {
    List<GuarantorAssessmentDetailsMicro> findAllByLoanId(String id);
}
