package com.untucapital.usuite.utg.pos.controller;

import com.untucapital.usuite.utg.pos.dto.POReportsRequestDto;
import com.untucapital.usuite.utg.pos.service.POReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**************************************
 ** @project utg
 ** @author Takunda Jimmy Chidanika    
 ** @created 2023/11/22                   
 **************************************
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/pos/reports")
public class POReportsController {
    private final POReportService poReportService;

    //    GET ALL REQUISITIONS WITH STATUS PAID OR UNPAID
    @GetMapping
    public void getAllRequisitions() {
        poReportService.getAllRequisitions();
    }

    //    GET REQUISITIONS REPORTS BY STATUS AND RANGE
    @PostMapping("/by-status-and-range")
    public List<POReportsRequestDto> getRequisitionsByStatusAndRange(@RequestBody POReportsRequestDto request) {
        return poReportService.getRequisitionsByStatusAndRange(request);
    }
}
