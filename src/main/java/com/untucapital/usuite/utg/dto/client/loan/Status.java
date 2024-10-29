package com.untucapital.usuite.utg.dto.client.loan;

import lombok.Data;

@Data
public class Status {
    private int id;
    private String code;
    private String value;
    private boolean pendingApproval;
    private boolean waitingForDisbursal;
    private boolean active;
    private boolean restructured;
    private boolean closedObligationsMet;
    private boolean closedWrittenOff;
    private boolean closedRescheduled;
    private boolean closed;
    private boolean overpaid;

    // For SavingsAccount status
    private boolean submittedAndPendingApproval;
    private boolean approved;
    private boolean rejected;
    private boolean withdrawnByApplicant;
    private boolean prematureClosed;
    private boolean transferInProgress;
    private boolean transferOnHold;
    private boolean matured;

    // Getters and Setters
    // ...
}
