package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timeline {
    private List<Integer> submittedOnDate;
    private String submittedByUsername;
    private String submittedByFirstname;
    private String submittedByLastname;
    private List<Integer> approvedOnDate;
    private String approvedByUsername;
    private String approvedByFirstname;
    private String approvedByLastname;
    private List<Integer> expectedDisbursementDate;
    private List<Integer> actualDisbursementDate;
    private String disbursedByUsername;
    private String disbursedByFirstname;
    private String disbursedByLastname;
    private List<Integer> closedOnDate;
    private List<Integer> expectedMaturityDate;



}
