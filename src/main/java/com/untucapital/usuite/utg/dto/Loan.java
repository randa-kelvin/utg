package com.untucapital.usuite.utg.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {

    private Integer id;
    private String accountNo;
    private BigDecimal feeChargesCharged;
    private BigDecimal feeChargesDueAtDisbursementCharged;
    private BigDecimal penaltyChargesCharged;
    private String externalId;
    private Integer clientId;
    private String clientAccountNo;
    private String clientName;
    private Integer clientOfficeId;
    private Integer loanProductId;
    private String loanProductName;
    private String officeName;
    private InterestRateFrequencyType interestRateFrequencyType;
    private String loanProductDescription;
    private boolean isLoanProductLinkedToFloatingRate;
    private Integer loanOfficerId;
    private String loanOfficerName;
    private BigDecimal principal;
    private Integer approvedPrincipal;
    private Integer proposedPrincipal;
    private Integer termFrequency;
    private Integer numberOfRepayments;
    private Integer repaymentEvery;
    private Double interestRatePerPeriod;
    private Integer annualInterestRate;
    private boolean isFloatingInterestRate;
    private boolean allowPartialPeriodInterestCalcualtion;
    private Integer transactionProcessingStrategyId;
    private String transactionProcessingStrategyName;
    private LocalDate expectedFirstRepaymentOnDate;

    private Timeline timeline;
    private Status status;
    private RepaymentSchedule repaymentSchedule;
    private Summary summary;
    private TermPeriodFrequencyType termPeriodFrequencyType;
    private Currency currency;


}
