package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

@Data
public class Summary {

    private Currency currency;
    private double totalDeposits;
    private double totalWithdrawals;
    private double totalInterestPosted;
    private double accountBalance;
    private double totalOverdraftInterestDerived;
    private double interestNotPosted;

}
