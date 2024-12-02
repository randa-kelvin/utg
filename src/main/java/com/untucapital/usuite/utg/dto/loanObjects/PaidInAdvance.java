package com.untucapital.usuite.utg.dto.loanObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaidInAdvance {
    private BigDecimal paidInAdvance;
}
