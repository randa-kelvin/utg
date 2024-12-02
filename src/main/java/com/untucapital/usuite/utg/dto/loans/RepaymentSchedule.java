package com.untucapital.usuite.utg.dto.loans;

import com.untucapital.usuite.utg.dto.Currency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class RepaymentSchedule {

    private Currency currency;
    private int loanTermInDays;
    private double totalPrincipalDisbursed;
    private double totalPrincipalExpected;
    private double totalPrincipalPaid;
    private double totalInterestCharged;
    private double totalFeeChargesCharged;
    private double totalPenaltyChargesCharged;
    private double totalWaived;
    private double totalWrittenOff;
    private double totalRepaymentExpected;
    private double totalRepayment;
    private double totalPaidInAdvance;
    private double totalPaidLate;
    private double totalOutstanding;
    private double internalRateOfReturn;
    private List<Period> periods;
}
