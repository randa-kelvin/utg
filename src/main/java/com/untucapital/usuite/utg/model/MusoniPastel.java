package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "musoni_pastel")
public class MusoniPastel {

    @NotNull
    @Id
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_date", nullable = false)
    private String transactionDate;

    @Column(name = "from_account", nullable = false)
    private String fromAccount;

    @Column(name = "to_account", nullable = false)
    private String toAccount;

    @Column(name = "reference", nullable
            = false)
    private String reference;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "amount", nullable = false)
    private String amount;

    @Column(name = "exchange_rate", nullable = false)
    private String exchangeRate;

    @Column(name = "synced", nullable = false)
    private String synced;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }
}
