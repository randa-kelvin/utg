package com.untucapital.usuite.utg.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@RequiredArgsConstructor
public class LoansPipelineDTO{

    private String branchName;
    private BigInteger caseloads;
    private Double disbursements;
    private Double pendingDisbursements;
    private Double prospects;
    private Double assessments;
    private Double totalPipeline;
    private BigDecimal newClients;
    private BigDecimal repeatClients;

}
