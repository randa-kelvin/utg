package com.untucapital.usuite.utg.controller;
import com.untucapital.usuite.utg.dto.request.DisbursementTicketRequestDTO;
import com.untucapital.usuite.utg.model.DisbursementTicket;
import com.untucapital.usuite.utg.repository.DisbursementTicketRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.DisbursementTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "disbursementTickets")
public class DisbursementTicketController extends AbstractController<DisbursementTicket> {
    @Autowired
    DisbursementTicketRepository disbursementTicketRepository;

    private static final Logger log = LoggerFactory.getLogger(DisbursementTicketController.class);

    private final DisbursementTicketService disbursementTicketService;

    public DisbursementTicketController(DisbursementTicketService disbursementTicketService) {
        this.disbursementTicketService = disbursementTicketService;
    }

    //build save branch REST API
    @PostMapping("/adddisbursementTicket")
    public void add(@RequestBody DisbursementTicketRequestDTO disbursementTicket) {
        disbursementTicketService.saveDisbursementTicket(disbursementTicket);
    }

    @DeleteMapping("/deleteDisbursementTicket/{id}")
    public void delete(@PathVariable String id) {

        disbursementTicketService.deleteDisbursementTicket(id);
    }

    @Override
    protected AbstractService<DisbursementTicket> getService() {
        return disbursementTicketService;
    }
}
