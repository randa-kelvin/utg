package com.untucapital.usuite.utg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisbursedLoan {
    private String loanName;
    private String loanId;
    private LocalDate expectedDisbursementDate;
    private LocalDate disbursedAt;
    private String disbursedMonth;
    private BigDecimal principal;
    private String branch;
}
