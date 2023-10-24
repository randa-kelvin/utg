package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
    private Currency currency;
    private double principalDisbursed;
    private double principalPaid;
    private double principalWrittenOff;
    private BigDecimal principalOutstanding;
    private BigDecimal principalOverdue;
    private BigDecimal interestCharged;
    private BigDecimal interestPaid;
    private BigDecimal interestWaived;
    private BigDecimal interestWrittenOff;
    private BigDecimal interestOutstanding;
    private BigDecimal interestOverdue;
    private BigDecimal feeChargesCharged;
    private BigDecimal feeChargesDueAtDisbursementCharged;
    private BigDecimal feeChargesPaid;
    private BigDecimal feeChargesWaived;
    private BigDecimal feeChargesWrittenOff;
    private BigDecimal feeChargesOutstanding;
    private BigDecimal feeChargesOverdue;
    private BigDecimal penaltyChargesCharged;
    private BigDecimal penaltyChargesPaid;
    private BigDecimal penaltyChargesWaived;
    private BigDecimal penaltyChargesWrittenOff;
    private BigDecimal penaltyChargesOutstanding;
    private BigDecimal penaltyChargesOverdue;
    private BigDecimal totalExpectedRepayment;
    private BigDecimal totalRepayment;
    private BigDecimal totalExpectedCostOfLoan;
    private BigDecimal totalCostOfLoan;
    private BigDecimal totalWaived;
    private BigDecimal totalWrittenOff;
    private BigDecimal totalRecovered;
    private BigDecimal totalOutstanding;
    private BigDecimal totalOverdue;
    private Integer daysInArrears;
}
