package com.untucapital.usuite.utg.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisbursedLoans {
    private BigDecimal totalPrincipalDisbursed;
    private List<DisbursedLoanMonth> disbursedLoanMonths;
}
