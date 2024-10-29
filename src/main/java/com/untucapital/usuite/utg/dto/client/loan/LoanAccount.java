package com.untucapital.usuite.utg.dto.client.loan;

import lombok.Data;

@Data
 public class LoanAccount {
    private int id;
    private String accountNo;
    private int productId;
    private String productName;
    private String shortProductName;
    private Status status;
    private LoanType loanType;
    private int loanCycle;
    private Timeline timeline;
    private boolean inArrears;
    private double originalLoan;
    private double loanBalance;
    private double amountPaid;
    private Currency currency;
    private int loanCounter;
    private int loanProductCounter;

    // Getters and Setters
    // ...
}
