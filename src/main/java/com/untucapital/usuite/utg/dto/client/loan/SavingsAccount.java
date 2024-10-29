package com.untucapital.usuite.utg.dto.client.loan;

import lombok.Data;

import java.util.List;

@Data
public class SavingsAccount {
    private int id;
    private String accountNo;
    private int productId;
    private String productName;
    private String shortProductName;
    private Status status;
    private Currency currency;
    private AccountType accountType;
    private Timeline timeline;
    private SubStatus subStatus;
    private List<Integer> lastActiveTransactionDate;
    private String clientAccountNo;
    private String clientDisplayName;
    private int clientId;
    private ClientStatus clientStatus;
    private DepositType depositType;

    // Getters and Setters
    // ...
}
