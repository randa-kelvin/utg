package com.untucapital.usuite.utg.dto.musoni.savingsaccounts.transactions;

import lombok.Data;

@Data
public class PaymentDetailData {

    private int id;
    private PaymentType paymentType;
    private String accountNumber;
    private String checkNumber;
    private String routingCode;
    private String receiptNumber;
    private String bankNumber;
}
