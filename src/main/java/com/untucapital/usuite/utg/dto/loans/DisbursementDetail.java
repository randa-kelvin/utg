package com.untucapital.usuite.utg.dto.loans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DisbursementDetail {

    private int id;
    private String expectedDisbursementDate;
    private double principal;
    private double approvedPrincipal;
    private boolean disbursed;
    private String expectedDisbursementDateStr;
    private String actualDisbursementDateStr;
    private boolean disbursedDataChanged;
    private boolean interestChargedFromDateSameAsDisbursementDate;
}
