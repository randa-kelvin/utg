package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.AppraisalFileUploadRequestDTO;
import com.untucapital.usuite.utg.dto.response.AppraisalFileUploadResponseDTO;
import com.untucapital.usuite.utg.repository.AppraisalFileUploadRepository;
import com.untucapital.usuite.utg.service.AppraisalFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appraisalFileUpload")
public class AppraisalFileUploadController {
    @Autowired
    private final AppraisalFileUploadService appraisalFileUploadService;

    @Autowired
    private final AppraisalFileUploadRepository appraisalFileUploadRepository;

    public AppraisalFileUploadController(AppraisalFileUploadService appraisalFileUploadService, AppraisalFileUploadRepository appraisalFileUploadRepository) {
        this.appraisalFileUploadService = appraisalFileUploadService;
        this.appraisalFileUploadRepository = appraisalFileUploadRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AppraisalFileUploadRequestDTO appraisalFileUpload){
        appraisalFileUploadService.save(appraisalFileUpload);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update existing Appraisal file
    @PutMapping("/update/{loanId}")
    public ResponseEntity<String> updateAppraisalFile(@PathVariable String loanId, @RequestBody AppraisalFileUploadRequestDTO appraisalFileUpload){

        appraisalFileUploadService.updateAppraisalFile(loanId, appraisalFileUpload);
        return new ResponseEntity<String>("Appraisal File updated", HttpStatus.OK);
    }

    @GetMapping("get/{loanId}")
    public List<AppraisalFileUploadResponseDTO> findByLoanId(@PathVariable String loanId){
        return appraisalFileUploadService.findByLoanId(loanId);
    }

    @GetMapping("getAppraisalFilePath/{loanId}")
    public String getAppraisalFilePath(@PathVariable String loanId){
        return appraisalFileUploadService.getAppraisalFilePath(loanId);
    }

}
