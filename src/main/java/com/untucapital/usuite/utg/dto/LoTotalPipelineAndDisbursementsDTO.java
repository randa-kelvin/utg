package com.untucapital.usuite.utg.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@RequiredArgsConstructor
public class LoTotalPipelineAndDisbursementsDTO {

    private String branchName;
    private String loanOfficer;
    private Double totalPipeline;
    private Double totalDisbursed;

}
