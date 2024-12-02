package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.ClientLoanEnquiryRequestDTO;
import com.untucapital.usuite.utg.dto.response.ClientLoanEnquiryResponseDTO;
import com.untucapital.usuite.utg.repository.ClientLoanEnquiryRepository;
import com.untucapital.usuite.utg.service.ClientLoanEnquiryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "credit_application_enquiry")
public class ClientLoanEnquiryController {

    @Autowired
    ClientLoanEnquiryRepository clientLoanEnquiryRepository;

    @Autowired
    ClientLoanEnquiryService clientLoanEnquiryService;

    private static final Logger log = LoggerFactory.getLogger(ClientLoanEnquiryController.class);

    //build save loan REST API
    @PostMapping
    public ResponseEntity<ClientLoanEnquiryResponseDTO> saveClientLoanEnquiry(@RequestBody ClientLoanEnquiryRequestDTO request) {
        log.info(String.valueOf(request));
        return new ResponseEntity<ClientLoanEnquiryResponseDTO>(clientLoanEnquiryService.saveClientLoanEnquiry(request), HttpStatus.CREATED);
    }

    //build get all loan applications REST API
    @GetMapping
    public List<ClientLoanEnquiryResponseDTO> getAllClientLoanEnquiries() {
        return clientLoanEnquiryService.getAllClientLoanEnquiries();
    }

    //build get clientLoan by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<ClientLoanEnquiryResponseDTO> getClientLoanEnquiryById(@PathVariable("id") String id) {
        return new ResponseEntity<ClientLoanEnquiryResponseDTO>(clientLoanEnquiryService.getClientLoanEnquiryById(id), HttpStatus.OK);
    }

    //build get clientLoan by ID REST API
    @GetMapping("/userid/{userId}")
    public ResponseEntity<List<ClientLoanEnquiryResponseDTO>> getClientLoanEnquiryByUserId(@PathVariable("userId") String userId) {
        return new ResponseEntity<List<ClientLoanEnquiryResponseDTO>>(clientLoanEnquiryService.getClientLoanEnquiryByUserId(userId), HttpStatus.OK);
    }

}




