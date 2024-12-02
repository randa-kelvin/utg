package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.BulkSMSDTO;
import com.untucapital.usuite.utg.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j

@RequestMapping(value = "sms", produces = "application/json")
public class SmsController {

    private final SmsService smsService;

    @GetMapping("single/{destination}/{messageText}")
    public String sendSingle(@PathVariable String destination, @PathVariable String messageText) {
        return smsService.sendSingle(destination, messageText);
    }

    @PostMapping("bulk")
    public String sendBulk(@RequestBody BulkSMSDTO bulkSMSDTO) {
        return smsService.sendBulkSMS(bulkSMSDTO);
    }

    @GetMapping("balance")
    public String getBalance() {
        return smsService.getBalance();
    }

//    @GetMapping("time")
//    public String getTime(){
//        return smsService.SchedulerConfig();
//    }


}
