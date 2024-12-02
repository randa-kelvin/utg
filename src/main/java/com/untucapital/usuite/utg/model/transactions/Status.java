package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

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

}
