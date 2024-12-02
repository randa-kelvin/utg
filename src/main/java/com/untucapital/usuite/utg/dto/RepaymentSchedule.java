package com.untucapital.usuite.utg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author tjchidanika
 * @created 27/6/2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepaymentSchedule {
    private Integer loanTermInDays;
    private BigDecimal totalPrincipalDisbursed;
    private List<Period> periods;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Period {
        private int period;
        private LocalDate fromDate;
        private LocalDate dueDate;
        private int daysInPeriod;
        private BigDecimal principalOutstanding;
        private BigDecimal interestOriginalDue;
        private BigDecimal interestDue;
        private BigDecimal interestPaid;
        private BigDecimal interestOutstanding;
    }
}
