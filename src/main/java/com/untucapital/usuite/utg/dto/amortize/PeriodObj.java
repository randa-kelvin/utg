package com.untucapital.usuite.utg.dto.amortize;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PeriodObj {

    private int period;
    private String month;
    private int month_int;
    private double PV;
    private double PMT;
    private double Interest;
    private double FV;
    private double PeriodInterest3;
    private double PeriodInterest2;

}
