package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class  AppraisalLoanRequestResponseDTO extends AbstractEntityDTO {

    private String userId;
    private String loanOfficerFirstName;
    private String loanOfficerLastName;
    private String loanId;
    private String investmentPlan;
    private String quotation;
    private String whyQuotation;
    private String loanToSupplier;
    private String whyLoanToSupplier;
    private String percentageFixedAssets;
    private String percentageWorkingCapital;
    private String source;
    private String sourceAmount;
    private String percentageShare;
    private String verified;
    private String comments;
    private String loanAmountClient;
    private String maturityClient;
    private String interestRateClient;
    private String installmentClient;
    private String loanAmountLoanOfficer;
    private String maturityLoanOfficer;
    private String interestRateLoanOfficer;
    private String installmentLoanOfficer;
    private String commentsTermAndCondition;
}








