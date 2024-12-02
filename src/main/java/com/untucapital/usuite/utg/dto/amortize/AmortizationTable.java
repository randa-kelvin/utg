package com.untucapital.usuite.utg.dto.amortize;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class AmortizationTable {

    private List<PeriodObj> AmortizationTable;
    private double TotalInterest;
    private double PeriodInterest1;

}
