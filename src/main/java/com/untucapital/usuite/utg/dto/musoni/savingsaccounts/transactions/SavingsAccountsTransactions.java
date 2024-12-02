package com.untucapital.usuite.utg.dto.musoni.savingsaccounts.transactions;

import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.Currency;
import lombok.Data;

import java.util.List;

@Data
public class SavingsAccountsTransactions {

    private int id;
    private TransactionType transactionType;
    private int accountId;
    private String accountNo;
    private int[] date;
    private Currency currency;
    private double amount;
    private double runningBalance;
    private boolean reversed;
    private int[] submittedOnDate;
    private boolean interestedPostedAsOn;
    private boolean manuallyReversed;
    private boolean isAccountTransfer;

    private PaymentDetailData paymentDetailData;

}
