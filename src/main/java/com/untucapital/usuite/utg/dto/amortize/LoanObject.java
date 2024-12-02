package com.untucapital.usuite.utg.dto.amortize;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoanObject {

    private String accountNo;
    private String status;
    private int clientId;
    private String clientName;
    private String loanProductName;
    private String loanOfficerName;
    private double principal;
    private int numberOfRepayments;
    private double interestRatePerPeriod;
    private String actualDisbursementDate;
    private String expectedMaturityDate;
    private AmortizationTable amortizationTable;
    private double totalInterest;
    private double periodInterest2;
    private double PeriodInterest4;
}
