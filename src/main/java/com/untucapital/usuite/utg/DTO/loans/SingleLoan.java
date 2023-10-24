package com.untucapital.usuite.utg.DTO.loans;

import com.untucapital.usuite.utg.DTO.Summary;
import com.untucapital.usuite.utg.model.transactions.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class SingleLoan {

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
    private boolean isLoanProductLinkedToFloatingRate;
    private int loanOfficerId;
    private String loanOfficerName;
    private LoanType loanType;
    private Currency currency;
    private double principal;
    private double approvedPrincipal;
    private double proposedPrincipal;
    private int termFrequency;
    private TermPeriodFrequencyType termPeriodFrequencyType;
    private int numberOfRepayments;
    private int repaymentEvery;
    private RepaymentFrequencyType repaymentFrequencyType;
    private double interestRatePerPeriod;
    private InterestRateFrequencyType interestRateFrequencyType;
    private double annualInterestRate;
    private boolean isFloatingInterestRate;
    private AmortizationType amortizationType;
    private InterestType interestType;
    private InterestCalculationPeriodType interestCalculationPeriodType;
    private boolean allowPartialPeriodInterestCalculation;
    private int transactionProcessingStrategyId;
    private String transactionProcessingStrategyName;
    private int[] expectedFirstRepaymentOnDate;
    private boolean syncDisbursementWithMeeting;
    private Timeline timeline;
    private Summary summary;
    private double feeChargesAtDisbursementCharged;
    private int loanCounter;
    private int loanProductCounter;
    private boolean multiDisburseLoan;
    private boolean canDefineInstallmentAmount;
    private boolean canDisburse;
    private boolean canUseForTopup;
    private boolean isTopup;
    private int closureLoanId;
    private boolean inArrears;
    private boolean isNPA;
    private int[] overdueCharges;
    private DaysInMonthType daysInMonthType;
    private DaysInYearType daysInYearType;
    private boolean isInterestRecalculationEnabled;
    private boolean createStandingInstructionAtDisbursement;
    private PaidInAdvance paidInAdvance;
    private boolean isVariableInstallmentsAllowed;
    private int minimumGap;
    private int maximumGap;
    private double internalRateOfReturn;
    private double effectiveInterestRate;
    private int repayPrincipalEvery;
    private int repayInterestEvery;
    private boolean isInDuplum;
    private String originChannel;
    private int officeId;
    private String officeName;
    private boolean stopApplyingPenalty;
    private boolean recalculateInterestOnArrears;
    private boolean syncInterestChargedFromDateWithActualDisbursementDate;
    private boolean spreadGracePeriodInterestOverRemainingPeriods;
}
