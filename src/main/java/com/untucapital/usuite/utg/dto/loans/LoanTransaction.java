package com.untucapital.usuite.utg.dto.loans;

import com.untucapital.usuite.utg.dto.Currency;
import com.untucapital.usuite.utg.model.transactions.PaymentDetailData;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class LoanTransaction {

    private int id;
    private int officeId;
    private String officeName;
    private LoanTransactionType type;
    private int[] date;
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
    private List<Integer> submittedOnDate;
    private String submittedUsername;
    private String submittedByFirstname;
    private String submittedByLastname;
    private boolean manuallyReversed;
    private boolean isReversed;
    private boolean isPrepayment;

}
