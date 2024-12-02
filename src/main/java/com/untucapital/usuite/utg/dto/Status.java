package com.untucapital.usuite.utg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    private Integer id;
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
