package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.AppraisalFileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppraisalFileUploadRepository extends JpaRepository<AppraisalFileUpload, String> {

    List<AppraisalFileUpload> findByLoanId(String loanId);

    AppraisalFileUpload findAppraisalFileUploadByLoanId(String loanId);
}
