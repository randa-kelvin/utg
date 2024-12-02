package com.untucapital.usuite.utg.pos.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class ExpenditureDto {


    private Integer id;

    private String category;

    private int year;

    private String month;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
