package com.untucapital.usuite.utg.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TimeLine {

    private List<Integer> submittedOnDate;
    private String submittedByUsername;
    private String submittedByFirstname;
    private String submittedByLastname;
    private List<Integer> activatedOnDate;
    private String activatedUsername;
    private String activatedByFirstname;
    private String activatedByLastname;
    private List<Integer> closedOnDate;
    private String closedUsername;
    private String closedByFirstname;
    private String closedByLastname;

}
