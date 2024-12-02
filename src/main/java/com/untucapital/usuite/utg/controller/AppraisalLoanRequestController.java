package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.AppraisalLoanRequest;
import com.untucapital.usuite.utg.service.AppraisalLoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/appraisalLoanRequest")
public class AppraisalLoanRequestController {
    @Autowired
    AppraisalLoanRequestService appraisalLoanRequestService;

    @GetMapping("getAppraisalLoanRequest")
    public List<AppraisalLoanRequest> list() {
        return appraisalLoanRequestService.listAllAppraisalLoanRequest();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppraisalLoanRequest> get(@PathVariable Integer id) {
        try {
            AppraisalLoanRequest appraisalLoanRequest = appraisalLoanRequestService.getAppraisalLoanRequest(id);
            return new ResponseEntity<AppraisalLoanRequest>(appraisalLoanRequest, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<AppraisalLoanRequest>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addAppraisalLoanRequest")
    public void add(@RequestBody AppraisalLoanRequest appraisalLoanRequest) {
        appraisalLoanRequestService.saveAppraisalLoanRequest(appraisalLoanRequest);
    }


    @DeleteMapping("/deleteAppraisalLoanRequest/{id}")
    public void delete(@PathVariable Integer id) {

        appraisalLoanRequestService.deleteAppraisalLoanRequest(id);
    }
}
