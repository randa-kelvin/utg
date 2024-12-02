package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.ClientFileUploadRequestDTO;
import com.untucapital.usuite.utg.dto.response.ClientFileUploadResponseDTO;
import com.untucapital.usuite.utg.service.ClientFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ClientFileUpload")
public class ClientFileUploadController {

    @Autowired
    ClientFileUploadService clientFileUploadService;

    private static final Logger log = LoggerFactory.getLogger(ClientFileUploadController.class);

    @PostMapping("/add")
    public ResponseEntity<String> addClientFileUpload(@RequestBody ClientFileUploadRequestDTO clientFileUpload){
        clientFileUploadService.save(clientFileUpload);
        return new ResponseEntity<String>("KYC Document uploaded", HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public List<ClientFileUploadResponseDTO> getClientFileUpload(@PathVariable String userId){
        return clientFileUploadService.getClientFileUpload(userId);
    }
}
