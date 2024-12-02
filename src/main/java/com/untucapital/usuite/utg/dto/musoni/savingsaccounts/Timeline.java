package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

import java.util.List;

@Data
public class Timeline {

    private List<Integer> submittedOnDate;
    private String submittedByUsername;
    private String submittedByFirstname;
    private String submittedByLastname;
    private List<Integer> approvedOnDate;
    private String approvedByUsername;
    private String approvedByFirstname;
    private String approvedByLastname;
    private List<Integer> activatedOnDate;
    private String activatedByUsername;
    private String activatedByFirstname;
    private String activatedByLastname;

}
