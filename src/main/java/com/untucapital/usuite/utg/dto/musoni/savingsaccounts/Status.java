package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

@Data
public class Status {

    private int id;
    private String code;
    private String value;
    private boolean submittedAndPendingApproval;
    private boolean approved;
    private boolean rejected;
    private boolean withdrawnByApplicant;
    private boolean active;
    private boolean closed;
    private boolean prematureClosed;
    private boolean transferInProgress;
    private boolean transferOnHold;
    private boolean matured;

}
