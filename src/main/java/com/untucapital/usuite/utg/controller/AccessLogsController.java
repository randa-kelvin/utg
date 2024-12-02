package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.AccessLogs;
import com.untucapital.usuite.utg.repository.AccessLogsRepository;
import com.untucapital.usuite.utg.service.AccessLogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "access_logs")
public class AccessLogsController {

    private final AccessLogsService accessLogsService;
    private final AccessLogsRepository accessLogsRepository;
    private static final Logger log = LoggerFactory.getLogger(ClientLoanController.class);

    public AccessLogsController(AccessLogsService accessLogsService, AccessLogsRepository accessLogsRepository)
    {
        this.accessLogsService = accessLogsService;
        this.accessLogsRepository = accessLogsRepository;
    }
    @GetMapping
    public List<AccessLogs> getAccessLogs(){
        return accessLogsService.getAccessLogs();
    }

    @PostMapping
    public ResponseEntity<AccessLogs> saveAccessLogs(@RequestBody AccessLogs accessLogs) {
        log.info(String.valueOf(accessLogs));
        return new ResponseEntity<AccessLogs>(accessLogsRepository.save(accessLogs), HttpStatus.CREATED);
    }


}
