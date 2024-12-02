package com.untucapital.usuite.utg.model.metabase;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MetabaseLoanData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "external_id")
    private String externalId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private MetabaseStatus metabaseStatus;

    @Column(name = "client_id")
    private int clientId;

    @Column(name = "client_account_no")
    private String clientAccountNo;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "loan_product_id")
    private int loanProductId;

    @Column(name = "loan_product_name")
    private String loanProductName;

    @Column(name = "principal")
    private double principal;

    @Column(name = "approved_principal")
    private double approvedPrincipal;

    @Column(name = "proposed_principal")
    private double proposedPrincipal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_type_id")
    private MetabaseLoanType loanType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_code")
    private MetabaseCurrency currency;

    @Column(name = "number_of_repayments")
    private int numberOfRepayments;

    @Column(name = "annual_interest_rate")
    private double annualInterestRate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "summary_id")
    private MetabaseSummary metabaseSummary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timeline_id")
    private MetabaseTimeline metabaseTimeline;

    // Getters and setters
}

