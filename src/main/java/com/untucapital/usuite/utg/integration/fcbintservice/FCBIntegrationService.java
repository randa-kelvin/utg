package com.untucapital.usuite.utg.integration.fcbintservice;

import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.Industry;
import com.untucapital.usuite.utg.model.fcb.Report;
import com.untucapital.usuite.utg.model.fcb.Response;
import com.untucapital.usuite.utg.model.fcb.enums.*;
import com.untucapital.usuite.utg.service.IndustryService;
import com.untucapital.usuite.utg.utils.DateTimeFormatterUtil;
import com.untucapital.usuite.utg.utils.FormatterUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.untucapital.usuite.utg.config.FCBConfig.*;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

@RequiredArgsConstructor
@Service
public class FCBIntegrationService {

    private static final Logger log = LoggerFactory.getLogger(FCBIntegrationService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final IndustryService industryService;

    public Optional<Response> performSearch(ClientLoan clientLoan) {

        clientLoan.setIndustryCode(String.valueOf(industryService.getIndustryIdByName(clientLoan.getIndustryCode())));

        MultiValueMap<String, Object> searchHeaders = createReqHeaders(clientLoan);

        HttpEntity<Object> searchEntity = new HttpEntity<>(searchHeaders);

        log.info("FCB Perform Search Request with Params - {}", FormatterUtil.toJson(searchHeaders));

        // ResponseEntity<Response> responseEntity = restTemplate.exchange(PERFORM_SEARCH_URL, HttpMethod.POST, searchEntity, Response.class);

        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(PERFORM_SEARCH_URL, searchEntity, Object.class);

        String jsonResponse = FormatterUtil.toJson(responseEntity.getBody());

        log.info("FCB Perform Search Response - {}", jsonResponse);

        Response checkResponse;
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {

            checkResponse = FormatterUtil.toFCBResponse(jsonResponse);

            // FCB is sending the Report inside the string property individual, converting it here
            List<Report> reports = FormatterUtil.toFCBReport(checkResponse.getIndividual());

            checkResponse.setReport(reports);

            log.info("Client Credit Check Response - {}", FormatterUtil.toJson(checkResponse));

            return Optional.of(checkResponse);

        } else {
            log.info("Credit Check Failed Response - {}", FormatterUtil.toJson(responseEntity.getBody()));
        }

        return Optional.empty();
    }

    private MultiValueMap<String, Object> createReqHeaders(ClientLoan clientLoan) {
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");

        headers.set("email", USERNAME);
        headers.set("password", PASSWORD);
        headers.set("search_purpose", SearchPurpose.LOAN_RENEWAL_REPEAT_LOAN.code);
        headers.set("names", clientLoan.getFirstName());
        headers.set("surname", clientLoan.getLastName());
        headers.set("gender", clientLoan.getGender().substring(0, 1));
        headers.set("national_id", clientLoan.getIdNumber());
        headers.set("nationality", "3");
        headers.set("dob", DateTimeFormatterUtil.fcbDobFormatter(clientLoan.getDateOfBirth()));
        headers.set("married", MaritalStatus.valueOf(clientLoan.getMaritalStatus().toUpperCase()).code);
        // headers.set("id", "");
        headers.set("passport", "");
        headers.set("drivers_license", "");
        headers.set("streetno", clientLoan.getStreetNo());
        headers.set("streetname", clientLoan.getStreetName());
        headers.set("suburb", clientLoan.getSuburb());
        headers.set("building", "");
        headers.set("city", clientLoan.getCity());
        // headers.set("pbag", "");
        headers.set("ind_email", "");
        headers.set("mobile", clientLoan.getPhoneNumber());
        headers.set("telephone", "");
        headers.set("employer", "");
        headers.set("employer_industry", clientLoan.getIndustryCode());
        headers.set("salary_band", Salary._0_TO_150.code);
        headers.set("loan_purpose", LoanPurpose.BUSINESS_IMPROVEMENT_LOAN.code);
        headers.set("loan_amount", clientLoan.getLoanAmount());
        headers.set("occupation_class", OccupationClass.N_A.code);
        headers.set("property_density", PropertyDensity.MEDIUM.code);
        headers.set("property_status", PropertyOwnership.PARENTS.code);
        return headers;
    }
}
