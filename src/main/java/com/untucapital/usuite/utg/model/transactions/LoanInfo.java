package com.untucapital.usuite.utg.model.transactions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanInfo {
    private int id;
    private String accountNo;
    private String externalId;
    private Status status;
    private int clientId;
    private String clientAccountNo;
    private String clientName;
    private int clientOfficeId;
    private int loanProductId;
    private String loanProductName;
    private String loanProductDescription;
    private int loanOfficerId;
    private String loanOfficerName;
    private LoanType loanType;
    private Currency currency;
    private int principal;
    private int approvedPrincipal;
    private int proposedPrincipal;
    private int termFrequency;
    private TermPeriodFrequencyType termPeriodFrequencyType;
    private int numberOfRepayments;
    private int repaymentEvery;
    private RepaymentFrequencyType repaymentFrequencyType;
    private int interestRatePerPeriod;
    private InterestRateFrequencyType interestRateFrequencyType;
    private int annualInterestRate;
    private AmortizationType amortizationType;
    private InterestType interestType;
    private InterestCalculationPeriodType interestCalculationPeriodType;
    private boolean allowPartialPeriodInterestCalculation;
    private int transactionProcessingStrategyId;
    private String transactionProcessingStrategyName;
    private List<Integer> expectedFirstRepaymentOnDate;
    private Timeline timeline;
    private boolean multiDisburseLoan;
    private boolean canDefineInstallmentAmount;
    private boolean canDisburse;
    private boolean canUseForTopup;
    private boolean isTopup;
    private boolean stopApplyingPenalty;
    private int closureLoanId;
    private boolean inArrears;
    private boolean isNPA;
    private DaysInMonthType daysInMonthType;
    private DaysInYearType daysInYearType;
    private boolean isInterestRecalculationEnabled;
    private boolean recalculateInterestOnArrears;
    private boolean createStandingInstructionAtDisbursement;
    private boolean isVariableInstallmentsAllowed;
    private int internalRateOfReturn;
    private int effectiveInterestRate;
    private int repayPrincipalEvery;
    private int repayInterestEvery;
    private boolean isInDuplum;
    private boolean officeId;
    private String officeName;
    private String originChannel;
    private AuditData auditData;

    // Add getters and setters
}

