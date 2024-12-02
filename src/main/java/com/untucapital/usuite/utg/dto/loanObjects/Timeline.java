package com.untucapital.usuite.utg.dto.loanObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {

    private String[] overdueCharges;
    private Integer[] submittedOnDate;
    private String submittedByUsername;
    private String submittedByFirstname;
    private String submittedByLastname;
    private Integer[] approvedOnDate;
    private String approvedByUsername;
    private String approvedByFirstname;
    private String approvedByLastname;
    private Integer[] expectedDisbursementDate;
    private Integer[] actualDisbursementDate;
    private String disbursedByUsername;
    private String disbursedByFirstname;
    private String disbursedByLastname;
    private Integer[] closedOnDate;
    private Integer[] expectedMaturityDate;

}
