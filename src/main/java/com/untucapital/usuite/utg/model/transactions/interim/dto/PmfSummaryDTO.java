package com.untucapital.usuite.utg.model.transactions.interim.dto;

import com.untucapital.usuite.utg.model.transactions.PaymentDetailData;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PmfSummaryDTO {
    private int id;
    private String officeName;
    private String name;  // Add this field for the transaction name
    private TransactionTypeDTO type;
    private LocalDate date;
    private CurrencyDTO currency;
    private double amount;
    private boolean penalty;  // Add this field for the penalty
    private String submittedByUsername;
    private double principalDisbursed;
    private TimelineDTO timeline;
}
