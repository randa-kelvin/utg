package com.untucapital.usuite.utg.dto.client.loan;

import lombok.Data;

import java.util.List;
@Data
public class AccountData {
    private List<LoanAccount> loanAccounts;
    private List<SavingsAccount> savingsAccounts;

    // Getters and Setters
    public List<LoanAccount> getLoanAccounts() {
        return loanAccounts;
    }

    public void setLoanAccounts(List<LoanAccount> loanAccounts) {
        this.loanAccounts = loanAccounts;
    }

    public List<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }
}
