package com.untucapital.usuite.utg.pos.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class ExpenditureResponseDto {


    private String category;

    private int year;

    private String month;

    private BigDecimal amount;
    private float totalAmount;

}
