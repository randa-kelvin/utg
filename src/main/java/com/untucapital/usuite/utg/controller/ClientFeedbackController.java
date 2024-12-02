package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.ClientFeedbackRequestDTO;
import com.untucapital.usuite.utg.dto.response.ClientFeedbackResponseDTO;
import com.untucapital.usuite.utg.repository.ClientFeedbackRepository;
import com.untucapital.usuite.utg.service.ClientFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "credit_application_feedback")
public class ClientFeedbackController {


    @Autowired
    ClientFeedbackRepository clientFeedbackRepository;

    @Autowired
    ClientFeedbackService clientFeedbackService;

    private static final Logger log = LoggerFactory.getLogger(ClientFeedbackController.class);

    //build save loan REST API
    @PostMapping
    public ResponseEntity<ClientFeedbackResponseDTO> saveClientFeedback(@RequestBody ClientFeedbackRequestDTO clientFeedback) {
        log.info(String.valueOf(clientFeedback));
        return new ResponseEntity<ClientFeedbackResponseDTO>(clientFeedbackService.saveClientFeedback(clientFeedback), HttpStatus.CREATED);
    }

    //build get all loan applications REST API
    @GetMapping
    public List<ClientFeedbackResponseDTO> getAllClientLoanEnquiries() {
        return clientFeedbackService.getAllClientFeedback();

    }

    //build get clientLoan by ID REST API
//    @GetMapping("{id}")
//    public ResponseEntity<ClientFeedback> getClientFeedbackById(@PathVariable("id") String id) {
//        return new ResponseEntity<ClientFeedback>((MultiValueMap<String, String>) clientFeedbackRepository.findClientFeedbackById(id), HttpStatus.OK);
//    }

    @GetMapping("{id}")
    public ResponseEntity<ClientFeedbackResponseDTO> getClientFeedbackById(@PathVariable("id") String id) {
        return new ResponseEntity<ClientFeedbackResponseDTO>(clientFeedbackService.getClientFeedbackById(id), HttpStatus.OK);
    }


    //build get clientLoan by ID REST API
    @GetMapping("/userid/{userId}")
    public ResponseEntity<List<ClientFeedbackResponseDTO>> getClientFeedbackByUserId(@PathVariable("userId") String userId) {
        return new ResponseEntity<List<ClientFeedbackResponseDTO>>(clientFeedbackService.getClientFeedbackByUserId(userId), HttpStatus.OK);
    }

}




