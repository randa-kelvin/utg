package com.untucapital.usuite.utg.model.transactions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInfo {

    private String toAccount;
    private String transactionType;
    private int exchangeRate;
    private String description;
    private String fromAccount;
    private String reference;
    private String currency;
    private Float amount;
    private String transactionDate;
    private String userName;

}
