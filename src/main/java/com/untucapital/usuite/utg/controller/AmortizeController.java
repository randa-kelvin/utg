package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.service.AmortizeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value ="amortize", produces="application/json")
@Component
@RequiredArgsConstructor
public class AmortizeController {

    @Autowired
    AmortizeService amortizeService;

    private static final Logger log = LoggerFactory.getLogger(AmortizeController.class);

    @GetMapping("table/{loanId}/{period}")
    public String getTable(@PathVariable String loanId, @PathVariable String period) {

            return amortizeService.getTable(loanId, period);
    }

    @GetMapping("loanInterest/{rangeStart}/{rangeEnd}/{period}")
    public String getLoanInterest(@PathVariable String rangeStart, @PathVariable String rangeEnd, @PathVariable String period)  {
        return  amortizeService.getLoanInterest(rangeStart,rangeEnd,period);
    }

    @GetMapping("getLoansDisbursedByDate/{rangeStart}/{rangeEnd}/{period}")
    public String getLoanByDate(@PathVariable String rangeStart, @PathVariable String rangeEnd, @PathVariable String period)  {
        return  amortizeService.getLoansDisbursedByDate(rangeStart,rangeEnd,period);
    }
}
