package com.untucapital.usuite.utg.model.transactions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    private int id;
    private int officeId;
    private String officeName;
    private TransactionType type;
    private List<Integer> date;
    private Currency currency;
    private PaymentDetailData paymentDetailData;
    private double amount;
    private double principalPortion;
    private double interestPortion;
    private double feeChargesPortion;
    private double penaltyChargesPortion;
    private double overpaymentPortion;
    private double unrecognizedIncomePortion;
    private double outstandingLoanBalance;
    private int[] submittedOnDate;
    private String submittedByUsername;
    private String submittedByFirstname;
    private String submittedByLastname;
    private boolean manuallyReversed;
    private boolean isReversed;
    private boolean isPrepayment;


}
