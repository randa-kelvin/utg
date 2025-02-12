package com.untucapital.usuite.utg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "AccountsReceivablesMicro")
public class AccountsReceivable extends AbstractEntity {
    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "due_since")
    private String dueSince;

    @Column(name = "expected_payment")
    private String expectedPayment;

    @Column(name = "payment_probability")
    private String paymentProbability;

    @Column(name = "debt_value")
    private String debtValue;

    public AccountsReceivable() {
    }

    public AccountsReceivable(String loanId, String customerName, String dueSince, String expectedPayment, String paymentProbability, String debtValue) {
        this.loanId = loanId;
        this.customerName = customerName;
        this.dueSince = dueSince;
        this.expectedPayment = expectedPayment;
        this.paymentProbability = paymentProbability;
        this.debtValue = debtValue;
    }

    @Override
    public String toString() {
        return "AccountsReceivable{" +
                "customerName='" + customerName + '\'' +
                ", dueSince='" + dueSince + '\'' +
                ", expectedPayment='" + expectedPayment + '\'' +
                ", paymentProbability='" + paymentProbability + '\'' +
                ", debtValue='" + debtValue + '\'' +
                '}';
    }
}
