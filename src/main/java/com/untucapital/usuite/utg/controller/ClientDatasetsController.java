package com.untucapital.usuite.utg.controller;

// import com.untucapital.usuite.utg.model.ClientFeedback;

import com.untucapital.usuite.utg.dto.request.ClientsDatasetsRequestDTO;
import com.untucapital.usuite.utg.dto.response.ClientsDatasetsResponseDTO;
import com.untucapital.usuite.utg.repository.ClientDatasetsRepository;
import com.untucapital.usuite.utg.service.ClientDatasetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "credit_application_client_datasets")
public class ClientDatasetsController {


    @Autowired
    ClientDatasetsRepository clientDatasetsRepository;

    @Autowired
    ClientDatasetsService clientDatasetsService;

    private static final Logger log = LoggerFactory.getLogger(ClientDatasetsController.class);

    //build save loan REST API
    @PostMapping
    public ResponseEntity<ClientsDatasetsResponseDTO> saveClientFeedback(@RequestBody ClientsDatasetsRequestDTO request) {
        log.info(String.valueOf(request));
        return new ResponseEntity<ClientsDatasetsResponseDTO>(clientDatasetsService.saveclientsDatasets(request), HttpStatus.CREATED);
    }

    //build get all loan applications REST API
    @GetMapping
    public List<ClientsDatasetsResponseDTO> getAllclientsDatasets() {
        return clientDatasetsService.getAllclientsDatasets();

    }

    //build get clientLoan by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<ClientsDatasetsResponseDTO> getclientsDatasetsById(@PathVariable("id") Long id) {
        return new ResponseEntity<ClientsDatasetsResponseDTO>( clientDatasetsService.getclientsDatasetsById(id), HttpStatus.OK);
    }
    //build get clientLoan by Branch REST API
    @GetMapping("/branchName/{branch}")
    public List<ClientsDatasetsResponseDTO> getClientsByBranch(@PathVariable ("branch") String branch) {
        return clientDatasetsService.getClientsByBranch(branch);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateclientsDatasets(@PathVariable Long id, @RequestBody ClientsDatasetsRequestDTO clientsDatasets){

        clientDatasetsService.updateclientsDatasets(id,clientsDatasets);
        return new ResponseEntity<String>("Client Dataset successfully updated.", HttpStatus.OK);
    }


    //build get clientLoan by ID REST API


}




