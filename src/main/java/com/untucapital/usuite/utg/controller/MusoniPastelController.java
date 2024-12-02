package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.MusoniPastel;
import com.untucapital.usuite.utg.repository.MusoniPastelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "musoni_pastel")
public class MusoniPastelController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MusoniPastelRepository musoniPastelRepository;

    HttpHeaders headers;

    private static final Logger log = LoggerFactory.getLogger(MusoniPastelController.class);

    //build save transactions REST API
    @PostMapping
    public ResponseEntity<MusoniPastel> saveTransaction(@RequestBody MusoniPastel musoniPastel) {
        log.info(String.valueOf(musoniPastel));
        return new ResponseEntity<MusoniPastel>(musoniPastelRepository.save(musoniPastel), HttpStatus.CREATED);
    }

    //build get all transactions REST API
    @GetMapping
    public List<MusoniPastel> getAllTransactions() {
        return musoniPastelRepository.findAll();

    }

    // show transactions by sync status
    @GetMapping("/transactions/{synced}")
    public ResponseEntity<List<MusoniPastel>> getTransBySyncStatus(@PathVariable("synced") String synced) {
        return new ResponseEntity<List<MusoniPastel>>(musoniPastelRepository.findBySynced(synced), HttpStatus.OK);
    }

    @PutMapping("/update/{transactionId}")
    public ResponseEntity<String> updateSyncState(@PathVariable String transactionId, @RequestBody MusoniPastel musoniPastel){
        MusoniPastel updateSyncState = musoniPastelRepository.findByTransactionId(transactionId);
        updateSyncState.setSynced(musoniPastel.getSynced());
        musoniPastelRepository.save(updateSyncState);
        return new ResponseEntity<String>("Transaction Synced successfully.", HttpStatus.OK);
    }

}
