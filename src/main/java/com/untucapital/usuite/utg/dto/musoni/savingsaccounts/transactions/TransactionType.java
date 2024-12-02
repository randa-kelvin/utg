package com.untucapital.usuite.utg.dto.musoni.savingsaccounts.transactions;


import lombok.Data;

@Data
public class TransactionType {

    private int id;
    private String code;
    private String value;
    private boolean deposit;
    private boolean dividendPayout;
    private boolean withdrawal;
    private boolean interestPosting;
    private boolean penaltyOnInterestPosting;
    private boolean feeDeduction;
    private boolean initiateTransfer;
    private boolean approveTransfer;
    private boolean withdrawTransfer;
    private boolean rejectTransfer;
    private boolean overdraftInterest;
    private boolean writtenoff;
    private boolean overdraftFee;
    private boolean guarantorInterestDeposit;
    private boolean withholdTax;
    private boolean escheat;
    private boolean reverseEscheat;
    private boolean quarterlyFee;
    private boolean writtenOffNegativeBalance;
    private boolean closedByPositiveBalance;

}
