package com.untucapital.usuite.utg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisbursedLoanMonth {

    private String month;
    private int numberOfDisbursedLoans;
    private List<BranchDisbursedLoan> branchDisbursedLoans;
    private BigDecimal totalPrincipal;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class BranchDisbursedLoan {
        private String branch;
        private int numberOfDisbursedLoans;
        private List<DisbursedLoan> disbursedLoans;
        private BigDecimal totalPrincipal;
    }

}
