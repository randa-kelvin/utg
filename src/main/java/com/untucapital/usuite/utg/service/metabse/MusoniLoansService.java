package com.untucapital.usuite.utg.service.metabse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.model.metabase.LoanData;
import com.untucapital.usuite.utg.repository.metabase.MusoniLoansRepository;
import com.untucapital.usuite.utg.service.RoleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
@Data
@RequiredArgsConstructor
@Configuration
public class MusoniLoansService {


    @Value("${musoni.url}")
    private String musoniUrl;
    @Value("${musoni.username}")
    private String musoniUsername;
    @Value("${musoni.password}")
    private String musoniPassword;
    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String musoniTenantId;
    @Value("${musoni.X_API_KEY}")
    private String musoniApiKey;

    @Autowired
    private final RestTemplate restTemplate;

    private final RestClient restClient;

    private final MusoniLoansRepository musoniLoansRepository;

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);


    public List<LoanData> saveMusoniLoansToUtg() throws JsonProcessingException {

        List<LoanData> musoniLoans = restClient.saveMusoniLoansToUtg();

        log.info("musoniLoans: {}", musoniLoans);

        return musoniLoansRepository.saveAll(musoniLoans);
    }

}

