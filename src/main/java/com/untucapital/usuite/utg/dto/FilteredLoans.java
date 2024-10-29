package com.untucapital.usuite.utg.dto;

import lombok.Data;

@Data
public class FilteredLoans {

    private String accountNo;
    private String clientName;
    private String clientMobile;
    private String officerName;
    private String loanOfficerName;
    private String principal;
    private String numberOfRepayments;
    private String daysInArrears;

}
