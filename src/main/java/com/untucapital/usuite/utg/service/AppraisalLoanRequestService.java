package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.AppraisalLoanRequest;
import com.untucapital.usuite.utg.repository.AppraisalLoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@javax.transaction.Transactional
public class AppraisalLoanRequestService {
    @Autowired
    private AppraisalLoanRequestRepository appraisalLoanRequestRepository;

    @Transactional(value = "transactionManager")
    public List<AppraisalLoanRequest> listAllAppraisalLoanRequest() {
        return appraisalLoanRequestRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void saveAppraisalLoanRequest(AppraisalLoanRequest appraisalLoanRequest) {
        appraisalLoanRequestRepository.save(appraisalLoanRequest);
    }

    @Transactional(value = "transactionManager")
    public AppraisalLoanRequest getAppraisalLoanRequest(Integer id) {
        return appraisalLoanRequestRepository.findById(id).get();
    }

    @Transactional(value = "transactionManager")
    public void deleteAppraisalLoanRequest(Integer id) {
        appraisalLoanRequestRepository.deleteById(id);
    }
}
