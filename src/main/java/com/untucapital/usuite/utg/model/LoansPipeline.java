package com.untucapital.usuite.utg.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "LoansPipeline")
public class LoansPipeline extends AbstractEntity {

//    @Id
//    @Column(name = "int_id")
//    private Long intId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "date_recorded")
    private String dateRecorded;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "sector")
    private String sector;

    @Column(name = "repeat_client")
    private String repeatClient;

    @Column(name = "sought_loan")
    private Double soughtLoan;

    @Column(name = "loan_status")
    private String loanStatus;

    @Column(name = "loan_officer")
    private String loanOfficer;

    @Column(name = "average_target")
    private  Double averageTarget;

    @Column(name = "collateral")
    private String collateral;

}
