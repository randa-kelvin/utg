package com.untucapital.usuite.utg.model.metabase;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MetabaseSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_code")
    private MetabaseCurrency currency;

    @Column(name = "principal_disbursed")
    private double principalDisbursed;

    @Column(name = "principal_paid")
    private double principalPaid;

    @Column(name = "principal_written_off")
    private double principalWrittenOff;

    @Column(name = "principal_outstanding")
    private double principalOutstanding;

    @Column(name = "principal_overdue")
    private int principalOverdue;

    @Column(name = "interest_charged")
    private double interestCharged;

    @Column(name = "interest_paid")
    private double interestPaid;

    @Column(name = "interest_waived")
    private double interestWaived;

    @Column(name = "interest_written_off")
    private double interestWrittenOff;

    @Column(name = "interest_outstanding")
    private double interestOutstanding;

    @Column(name = "interest_overdue")
    private int interestOverdue;

    @Column(name = "fee_charges_charged")
    private double feeChargesCharged;

    @Column(name = "fee_charges_due_at_disbursement_charged")
    private double feeChargesDueAtDisbursementCharged;

    @Column(name = "fee_charges_paid")
    private double feeChargesPaid;

    @Column(name = "fee_charges_waived")
    private double feeChargesWaived;

    @Column(name = "fee_charges_written_off")
    private double feeChargesWrittenOff;

    @Column(name = "fee_charges_outstanding")
    private double feeChargesOutstanding;

    @Column(name = "fee_charges_overdue")
    private int feeChargesOverdue;

    @Column(name = "penalty_charges_charged")
    private double penaltyChargesCharged;

    @Column(name = "penalty_charges_paid")
    private double penaltyChargesPaid;

    @Column(name = "penalty_charges_waived")
    private double penaltyChargesWaived;

    @Column(name = "penalty_charges_written_off")
    private double penaltyChargesWrittenOff;

    @Column(name = "penalty_charges_outstanding")
    private double penaltyChargesOutstanding;

    @Column(name = "penalty_charges_overdue")
    private int penaltyChargesOverdue;

    @Column(name = "total_expected_repayment")
    private double totalExpectedRepayment;

    @Column(name = "total_repayment")
    private double totalRepayment;

    @Column(name = "total_expected_cost_of_loan")
    private double totalExpectedCostOfLoan;

    @Column(name = "total_cost_of_loan")
    private double totalCostOfLoan;

    @Column(name = "total_waived")
    private double totalWaived;

    @Column(name = "total_written_off")
    private double totalWrittenOff;

    @Column(name = "total_recovered")
    private int totalRecovered;

    @Column(name = "total_outstanding")
    private double totalOutstanding;

    @Column(name = "total_overdue")
    private int totalOverdue;

    // Getters and setters
}
