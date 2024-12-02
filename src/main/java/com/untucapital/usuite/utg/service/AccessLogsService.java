package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.AccessLogs;
import com.untucapital.usuite.utg.repository.AccessLogsRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AccessLogsService {

    private final AccessLogsRepository accessLogsRepository;

    public AccessLogsService(AccessLogsRepository accessLogsRepository) {
        this.accessLogsRepository = accessLogsRepository;
    }

    @Transactional(value = "transactionManager")
    public List<AccessLogs> getAccessLogs() {
        return accessLogsRepository.findAll();
    }
}
