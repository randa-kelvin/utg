package com.untucapital.usuite.utg.dto.client.loan;

import lombok.Data;

import java.util.List;
@Data
public class Timeline {
    private int[] submittedOnDate;
    private String submittedByUsername;
    private String submittedByFirstname;
    private String submittedByLastname;
    private int[] approvedOnDate;
    private String approvedByUsername;
    private String approvedByFirstname;
    private String approvedByLastname;
    private int[] expectedDisbursementDate;
    private int[] actualDisbursementDate;
    private String disbursedByUsername;
    private String disbursedByFirstname;
    private String disbursedByLastname;
    private int[] expectedMaturityDate;
    private int[] activatedOnDate;
    private String activatedByUsername;
    private String activatedByFirstname;
    private String activatedByLastname;

    // Getters and Setters
    // ...
}
