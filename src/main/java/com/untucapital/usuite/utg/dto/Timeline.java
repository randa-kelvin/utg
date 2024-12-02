package com.untucapital.usuite.utg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {

    private String[] overdueCharges;
    private LocalDate submittedOnDate;
    private String submittedByUsername;
    private String submittedByFirstname;
    private String submittedByLastname;
    private LocalDate approvedOnDate;
    private String approvedByUsername;
    private String approvedByFirstname;
    private String approvedByLastname;
    private LocalDate expectedDisbursementDate;
    private LocalDate actualDisbursementDate;
    private String disbursedByUsername;
    private String disbursedByFirstname;
    private String disbursedByLastname;
    private LocalDate closedOnDate;
    private LocalDate expectedMaturityDate;


}
