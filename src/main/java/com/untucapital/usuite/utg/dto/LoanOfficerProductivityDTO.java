package com.untucapital.usuite.utg.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@RequiredArgsConstructor
public class LoanOfficerProductivityDTO {

    private String branchName;
    private String loanOfficer;
    private BigDecimal disbursed;
    private BigDecimal pipelineCases;
    private BigInteger total;
    private Double averageTarget;
    private Double variance;

}
