package com.untucapital.usuite.utg.dto.response;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoansPipelineResponseDTO extends AbstractEntityDTO {
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
