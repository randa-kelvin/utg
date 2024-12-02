package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.MostImportantClientsRequestDTO;
import com.untucapital.usuite.utg.dto.response.MostImportantClientsResponseDTO;
import com.untucapital.usuite.utg.service.MostImportantClientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/most_important_clients")
public class MostImportantClientsController {
    @Autowired
    MostImportantClientsService mostImportantaClientsService;

    private static final Logger log = LoggerFactory.getLogger(OwnershipDetailsController.class);

    @GetMapping("/get/{loanId}")
    public List<MostImportantClientsResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return mostImportantaClientsService.getMostImportantClientsByLoanId(loanId);
    }

    @PostMapping("/most_importanta_clients")
    public void add(@RequestBody MostImportantClientsRequestDTO mostImportantClients) {
        mostImportantaClientsService.saveMostImportantClients(mostImportantClients);
    }

    @DeleteMapping("/delete_most_importanta_clients/{id}")
    public void delete(@PathVariable("id") String id) {
        mostImportantaClientsService.deleteMostImportantCliennts(id);
    }


}
