package com.untucapital.usuite.utg.model.transactions.interim.dto;

import lombok.Data;

import java.util.List;

@Data
public class SavingsTransactionDTO {
    private int id;
    private TransactionTypeDTO transactionType;
    private int accountId;
    private String accountNo;
    private List<Integer> date;
    private CurrencyDTO currency;
    private double amount;
    private double runningBalance;
    private boolean reversed;
    private TransferDTO transfer;
    private List<Integer> submittedOnDate;
    private boolean interestedPostedAsOn;
    private boolean manuallyReversed;
    private boolean isAccountTransfer;
}
