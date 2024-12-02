package com.untucapital.usuite.utg.dto.loans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Period {

    private int[] dueDate;
    private double principalDisbursed;
    private double principalLoanBalanceOutstanding;
    private double feeChargesDue;
    private double feeChargesPaid;
    private double totalOriginalDueForPeriod;
    private double totalDueForPeriod;
    private double totalPaidForPeriod;
    private double totalActualCostOfLoanForPeriod;
    private int modifiedByHoliday;
    private int period;
    private int[] obligationsMetOnDate;
    private double totalOutstandingForPeriod;
}
