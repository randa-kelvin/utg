package com.untucapital.usuite.utg.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "musoni_clients")
@Data
public class MusoniClient extends AbstractEntity{

    @NotNull
    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @Column(name = "external_id", nullable = false)
    private String externalId;

    private String status;

    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @Column(name = "activation_date", nullable = false)
    private String activationDate;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable=false)
    private String lastname;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "office_name", nullable = false)
    private String officeName;

    @Column(name = "submitted_by_username", nullable = false)
    private String submittedByUsername;

    @Column(name = "savings_account_id", nullable = false)
    private String savingsAccountId;

}
