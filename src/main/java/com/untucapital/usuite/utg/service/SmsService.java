package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.DTO.Bulk;
import com.untucapital.usuite.utg.DTO.BulkSMS;
import com.untucapital.usuite.utg.DTO.BulkSMSDTO;
import com.untucapital.usuite.utg.DTO.SMSDto;
import com.untucapital.usuite.utg.repository.SMSRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
@Slf4j
public class SmsService {
    private final SMSRepo smsRepo;
    private final RestTemplate restTemplate;

    @Value("${esolutions.url}")
    private String eSolutionsBaseURL;
    @Value("${esolutions.username}")
    private String username;
    @Value("${esolutions.password}")
    private String password;

    public SmsService(SMSRepo smsRepo, RestTemplate restTemplate) {
        this.smsRepo = smsRepo;
        this.restTemplate = restTemplate;
    }


    public HttpHeaders setESolutionsHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(username, password);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    public String sendSingle(String destination, String messageText) {
        if (!destination.startsWith("0") && !destination.startsWith("2")) {
            destination = "0" + destination;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String messageDate = dateFormat.format(new Date()) + 5;
        String messageReference = UUID.randomUUID().toString();
        SMSDto smsDto = new SMSDto("UNTU", destination, messageText, messageReference, messageDate, "", "");
        HttpEntity<SMSDto> entity = new HttpEntity<>(smsDto, setESolutionsHeaders());
        return restTemplate.exchange(eSolutionsBaseURL + "single", HttpMethod.POST, entity, String.class).getBody();
    }

    public String sendBulkSMS(BulkSMSDTO bulkSMSDTO) {

        List<BulkSMS> bulkSMS = new ArrayList<>();

        for (String phoneNumber : bulkSMSDTO.getPhoneNumbers()
        ) {
            String messageReference = "R" + UUID.randomUUID();
            bulkSMS.add(new BulkSMS("UNTU", phoneNumber, bulkSMSDTO.getMessage(), messageReference));
        }

        String batchNumber = "B" + UUID.randomUUID();
        Bulk bulk = Bulk.builder()
                .batchNumber(batchNumber)
                .messages(bulkSMS)
                .build();

        HttpEntity<Bulk> entity = new HttpEntity<>(bulk, setESolutionsHeaders());
        return restTemplate.exchange(eSolutionsBaseURL + "bulk", HttpMethod.POST, entity, String.class).getBody();
    }

    private String sendSMS(List<Map> listSMS1) {
        String batchNumber = "B" + UUID.randomUUID();
        Map<String, Object> json = new HashMap<>();
        json.put("batchNumber", batchNumber);
        json.put("messages", listSMS1);
        String json1 = new JSONObject(json).toString();
        System.out.printf("Bulk Message => %s%n", json1);
        HttpEntity<String> entity = new HttpEntity<>(json1, setESolutionsHeaders());
        return restTemplate.exchange(eSolutionsBaseURL + "bulk", HttpMethod.POST, entity, String.class).getBody();
    }

    public String getBalance() {
        HttpEntity<String> entity = new HttpEntity<>(setESolutionsHeaders());
        return restTemplate.exchange(eSolutionsBaseURL + "balance/UNTUAPI", HttpMethod.GET, entity, String.class).getBody();
    }

//    @Scheduled(fixedRate = 5000)
//    public String SchedulerConfig() {
//        System.out.println("The time is now {}");
//        return "The time is now {}";
//    }

}
