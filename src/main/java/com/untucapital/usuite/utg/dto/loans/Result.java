package com.untucapital.usuite.utg.dto.loans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Result {

    private List<DisbursedLoans> disbursedLoans;
    private List<Double> monthlyPrincipalTotals;

}
