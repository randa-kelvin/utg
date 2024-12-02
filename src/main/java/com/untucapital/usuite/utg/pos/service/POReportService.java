package com.untucapital.usuite.utg.pos.service;

import com.untucapital.usuite.utg.pos.dto.POReportsRequestDto;
import com.untucapital.usuite.utg.service.RequisitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * * @project utg
 * * @author Takunda Jimmy Chidanika
 * * @created 2023/11/22
 * *************************************
 */

@Service
@RequiredArgsConstructor
public class POReportService {


    private RequisitionService requisitionService;

    //    GET ALL REQUISITIONS WITH STATUS PAID OR UNPAID
    public void getAllRequisitions() {

    }

    //    GET REQUISITIONS REPORTS BY STATUS AND RANGE
    public List<POReportsRequestDto> getRequisitionsByStatusAndRange(POReportsRequestDto request) {

        List<POReportsRequestDto> reports = new ArrayList<>();

        reports.add(request);

        return reports;
    }
}
