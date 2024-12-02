package com.untucapital.usuite.utg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScheduledTask {

//    private final MusoniService musoniService;
//
//    // Run at 5 AM every day
//    @Scheduled(cron = "0 0 5 * * ?")
//    public void runAtFiveAM() throws ParseException, JsonProcessingException, AccountNotFoundException {
//
//        Long startTime = MusoniUtils.getUnixTimeMinus24Hours();
//        musoniService.getLoansByTimestamp(startTime);
//    }
}
