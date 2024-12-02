package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.AssessmentFileUploadRequestDTO;
import com.untucapital.usuite.utg.dto.response.AssessmentFileUploadResponseDTO;
import com.untucapital.usuite.utg.service.AssessmentFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessmentFileUpload")
public class AssessmentFileUploadController {

    @Autowired
    AssessmentFileUploadService assessmentFileUploadService;

    private static final Logger log = LoggerFactory.getLogger(AssessmentFileUploadController.class);

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody AssessmentFileUploadRequestDTO assessmentFileUpload){
//        System.out.println(assessmentFileUpload.getFileName());
        assessmentFileUploadService.save(assessmentFileUpload);
        return new ResponseEntity<String>("Assessment file uploaded", HttpStatus.OK);
    }

    @GetMapping("/get/{loanId}")
    public List<AssessmentFileUploadResponseDTO> get(@PathVariable String loanId){
        return assessmentFileUploadService.getAssessmentFileUpload(loanId);

    }

}
