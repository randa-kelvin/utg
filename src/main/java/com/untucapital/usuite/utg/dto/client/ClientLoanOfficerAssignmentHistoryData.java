package com.untucapital.usuite.utg.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientLoanOfficerAssignmentHistoryData {

    private int staffId;
    private String lastName;
    private String firstName;
    private List<Integer> startDate;
}
