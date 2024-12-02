package com.untucapital.usuite.utg.model.transactions.interim.dto;

import lombok.Data;

@Data
public class PaymentDetailDataDTO {
    private Long id;
    private PaymentTypeDTO paymentType;
    private String receiptNumber;
}
