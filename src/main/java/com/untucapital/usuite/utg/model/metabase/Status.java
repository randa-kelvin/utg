package com.untucapital.usuite.utg.model.metabase;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;
    private String value;

    @Column(name = "pending_approval")
    private boolean pendingApproval;

    @Column(name = "waiting_for_disbursal")
    private boolean waitingForDisbursal;

    private boolean active;
    private boolean restructured;

    @Column(name = "closed_obligations_met")
    private boolean closedObligationsMet;

    @Column(name = "closed_written_off")
    private boolean closedWrittenOff;

    @Column(name = "closed_rescheduled")
    private boolean closedRescheduled;

    private boolean closed;
    private boolean overpaid;

    // Lombok @Data will generate all getters, setters, and other methods
}
