package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.AssessmentFileUploadRequestDTO;
import com.untucapital.usuite.utg.dto.response.AssessmentFileUploadResponseDTO;
import com.untucapital.usuite.utg.model.AssessmentFileUpload;
import com.untucapital.usuite.utg.repository.AssessmentFileUploadRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class AssessmentFileUploadService {

    @Autowired
    AssessmentFileUploadRepository assessmentFileUploadRepository;

    @Transactional(value = "transactionManager")
    public void save(AssessmentFileUploadRequestDTO request){

        AssessmentFileUpload assessmentFileUpload = new AssessmentFileUpload();
        BeanUtils.copyProperties(request, assessmentFileUpload);
        assessmentFileUploadRepository.save(assessmentFileUpload);
    }

    @Transactional(value = "transactionManager")
    public List<AssessmentFileUploadResponseDTO> getAssessmentFileUpload(String loanId){

        List<AssessmentFileUploadResponseDTO> response = new ArrayList<>();
        List<AssessmentFileUpload> assessmentFileUploadList= assessmentFileUploadRepository.findAssessmentFileUploadByLoanId(loanId);

        for(AssessmentFileUpload assessmentFileUpload: assessmentFileUploadList){

            AssessmentFileUploadResponseDTO assessmentFileUploadResponse = new AssessmentFileUploadResponseDTO();
            BeanUtils.copyProperties(assessmentFileUpload,assessmentFileUploadResponse);

            response.add(assessmentFileUploadResponse);

        }

        return response;
    }

//    public void deleteAssessmentFileUpload();
}
