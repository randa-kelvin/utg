package com.untucapital.usuite.utg.dto.loans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoanBalance {

    private String accountNo;
    private String principalDisbursed;
    private String amountPaid;
    private String amountOverdue;
    private String disbursmentDate;
    private String status;
    private String numberOfRepayments;
    private String maturityDate;
}
