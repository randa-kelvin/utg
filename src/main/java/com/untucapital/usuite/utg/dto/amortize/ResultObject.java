package com.untucapital.usuite.utg.dto.amortize;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultObject {

    private AmortizationTable AmortizationTable;
    private double TotalInterest;
    private double PeriodInterest1;

}
