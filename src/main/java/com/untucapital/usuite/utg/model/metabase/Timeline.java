package com.untucapital.usuite.utg.model.metabase;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Timeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ElementCollection
    @CollectionTable(name = "submitted_on_date", joinColumns = @JoinColumn(name = "timeline_id"))
    @Column(name = "date")
    private List<Integer> submittedOnDate;

    @Column(name = "submitted_by_username")
    private String submittedByUsername;

    @Column(name = "submitted_by_firstname")
    private String submittedByFirstname;

    @Column(name = "submitted_by_lastname")
    private String submittedByLastname;

    @ElementCollection
    @CollectionTable(name = "approved_on_date", joinColumns = @JoinColumn(name = "timeline_id"))
    @Column(name = "date")
    private List<Integer> approvedOnDate;

    @Column(name = "approved_by_username")
    private String approvedByUsername;

    @Column(name = "approved_by_firstname")
    private String approvedByFirstname;

    @Column(name = "approved_by_lastname")
    private String approvedByLastname;

    @ElementCollection
    @CollectionTable(name = "expected_disbursement_date", joinColumns = @JoinColumn(name = "timeline_id"))
    @Column(name = "date")
    private List<Integer> expectedDisbursementDate;

    @ElementCollection
    @CollectionTable(name = "actual_disbursement_date", joinColumns = @JoinColumn(name = "timeline_id"))
    @Column(name = "date")
    private List<Integer> actualDisbursementDate;

    @Column(name = "disbursed_by_username")
    private String disbursedByUsername;

    @Column(name = "disbursed_by_firstname")
    private String disbursedByFirstname;

    @Column(name = "disbursed_by_lastname")
    private String disbursedByLastname;

    @ElementCollection
    @CollectionTable(name = "closed_on_date", joinColumns = @JoinColumn(name = "timeline_id"))
    @Column(name = "date")
    private List<Integer> closedOnDate;

    @ElementCollection
    @CollectionTable(name = "expected_maturity_date", joinColumns = @JoinColumn(name = "timeline_id"))
    @Column(name = "date")
    private List<Integer> expectedMaturityDate;

    // Getters and setters
}
