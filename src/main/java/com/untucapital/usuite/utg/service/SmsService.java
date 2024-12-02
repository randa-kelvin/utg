package com.untucapital.usuite.utg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.dto.Bulk;
import com.untucapital.usuite.utg.dto.BulkSMS;
import com.untucapital.usuite.utg.dto.BulkSMSDTO;
import com.untucapital.usuite.utg.dto.SMSDto;
import com.untucapital.usuite.utg.dto.sms.BulkMessage;
import com.untucapital.usuite.utg.exception.SmsException;
import com.untucapital.usuite.utg.repository.SMSRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
@RequiredArgsConstructor
@Data
public class SmsService {
    private final SMSRepo smsRepo;
    private final RestTemplate restTemplate;

    @Value("${esolutions.url}")
    private String eSolutionsBaseURL;
    @Value("${esolutions.username}")
    private String username;
    @Value("${esolutions.password}")
    private String password;


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
//        Sms sms = new Sms("UNTU",destination,messageText,messageReference,messageDate,"","");
        SMSDto smsDto = new SMSDto("UNTU", destination, messageText, messageReference, messageDate, "", "");
        HttpEntity<SMSDto> entity = new HttpEntity<>(smsDto, setESolutionsHeaders());
        try {
            return restTemplate.exchange(eSolutionsBaseURL + "single", HttpMethod.POST, entity, String.class).getBody();
        }catch (Exception e){
            throw new SmsException(e.getMessage()+ ". Please try to singup again");
        }
    }

    public String sendBulkSMS(BulkSMSDTO bulkSMSDTO) {

        List<BulkSMS> bulkSMS = new ArrayList<>();

        for (String phoneNumber: bulkSMSDTO.getPhoneNumbers()
        ) {
            String messageReference = "R"+UUID.randomUUID();
            bulkSMS.add(new BulkSMS("UNTU",phoneNumber, bulkSMSDTO.getMessage(),messageReference));
        }

        String batchNumber = "B" + UUID.randomUUID();
        Bulk bulk = Bulk.builder()
                .batchNumber(batchNumber)
                .messages(bulkSMS)
                .build();

        HttpEntity<Bulk> entity = new HttpEntity<>(bulk, setESolutionsHeaders());
        return restTemplate.exchange(eSolutionsBaseURL + "bulk", HttpMethod.POST, entity, String.class).getBody();
    }

//    private String sendSMS(List<Map> listSMS1) {
//        String batchNumber = "B" + UUID.randomUUID();
//        Map<String, Object> json = new HashMap<>();
//        json.put("batchNumber", batchNumber);
//        json.put("messages", listSMS1);
//        String json1 = new JSONObject(json).toString();
//        System.out.printf("Bulk Message => %s%n", json1);
//        HttpEntity<String> entity = new HttpEntity<>(json1, setESolutionsHeaders());
//        return restTemplate.exchange(eSolutionsBaseURL + "bulk", HttpMethod.POST, entity, String.class).getBody();
//    }


    private String sendSMS(List<Map> listSMS1) throws  JsonProcessingException {
        String batchNumber = "B" + UUID.randomUUID();

        BulkMessage bulkMessage = new BulkMessage();
        bulkMessage.setBatchNumber(batchNumber);
        bulkMessage.setMessages(listSMS1);

        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Serialize the BulkMessage object to JSON
        String json1 = objectMapper.writeValueAsString(bulkMessage);

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
