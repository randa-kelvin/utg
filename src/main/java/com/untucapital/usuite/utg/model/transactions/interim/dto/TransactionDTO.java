package com.untucapital.usuite.utg.model.transactions.interim.dto;

import com.untucapital.usuite.utg.model.transactions.Currency;
import com.untucapital.usuite.utg.model.transactions.PaymentDetailData;
import com.untucapital.usuite.utg.model.transactions.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private int id;
    private String officeName;
    private TransactionTypeDTO type;
    private LocalDate date;
    private LocalDate submittedOnDate;
    private CurrencyDTO currency;
    private PaymentDetailData paymentDetailData;
    private double amount;
    private String submittedByUsername;
    private TransferDTO transfer;

    private TimelineDTO timeline;
    private PmfSummaryDTO loanSummary;
}

