
package com.untucapital.usuite.utg.controller.metabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.controller.MusoniPastelController;
import com.untucapital.usuite.utg.model.metabase.LoanData;
import com.untucapital.usuite.utg.service.metabse.MusoniLoansService;
import com.untucapital.usuite.utg.service.pdfGeneratorService.LoanStatementPdfGeneratorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping(value ="metabase/musoni", produces="application/json")
@RequiredArgsConstructor
public class MusoniData {


    private static final Logger log = LoggerFactory.getLogger(MusoniPastelController.class);
    @Autowired
    RestTemplate restTemplate;

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

    private final MusoniLoansService musoniLoansService;

    @Autowired
    private LoanStatementPdfGeneratorService loanStatementPdfGeneratorService;

    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(musoniUsername,musoniPassword);
        headers.set("X-Fineract-Platform-TenantId",musoniTenantId);
        headers.set("x-api-key",musoniApiKey);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<String>(httpHeaders());
    }

    private HttpHeaders headers;


    @GetMapping("saveMusoniLoansToUtg")
    public List<LoanData> saveMusoniLoansToUtg() throws JsonProcessingException {
        return musoniLoansService.saveMusoniLoansToUtg();
    }




    //Get Loan By Id
    @GetMapping("loans/{loanId}")
    public String getLoanById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans/"+loanId, HttpMethod.GET, entity, String.class).getBody();
    }



}
