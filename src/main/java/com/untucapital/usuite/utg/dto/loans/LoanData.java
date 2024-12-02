package com.untucapital.usuite.utg.dto.loans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoanData {

    private String accountNo;
    private String clientName;
    private String loanProductName;
    private double principal;
    private int numberOfRepayments;
    private double interestRatePerPeriod;
    private String actualDisbursementDate;
    private String expectedMaturityDate;
    private double totalExpectedRepayment;
    private double totalRepayment;
    private double totalOutstanding;
    private String officeName;
    private String loanOfficerName;
    private double monthlyTotal;


}
