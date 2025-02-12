package com.untucapital.usuite.utg.dto.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoansPipelineRequestDTO {

    private Long intId;

    private String userId;

    private String branchName;

    private String dateRecorded;

    private String applicant;

    private String sector;

    private String repeatClient;

    private Double soughtLoan;

    private String loanStatus;

    private String loanOfficer;

    private String collateral;

}
