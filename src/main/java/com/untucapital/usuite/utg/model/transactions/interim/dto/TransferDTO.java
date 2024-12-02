package com.untucapital.usuite.utg.model.transactions.interim.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransferDTO {
    private Long id;
    private Boolean reversed;
    private CurrencyDTO currency;
    private Double transferAmount;
    private List<Integer> transferDate;
    private String transferDescription;
}
