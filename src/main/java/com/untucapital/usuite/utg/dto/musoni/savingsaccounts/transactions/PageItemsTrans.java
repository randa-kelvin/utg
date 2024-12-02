package com.untucapital.usuite.utg.dto.musoni.savingsaccounts.transactions;

import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.*;
import lombok.Data;

import java.util.List;

@Data
public class PageItemsTrans {

    private int id;
    private String accountNo;
    private DepositType depositType;
    private String externalId;
    private int clientId;
    private String clientName;
    private int savingsProductId;
    private String savingsProductName;
    private int fieldOfficerId;
    private String fieldOfficerName;
    private Status status;
    private SubStatus subStatus;
    private Timeline timeline;
    private Currency currency;
    private double nominalAnnualInterestRate;
    private InterestCompoundingPeriodType interestCompoundingPeriodType;
    private InterestPostingPeriodType interestPostingPeriodType;
    private InterestCalculationType interestCalculationType;
    private InterestCalculationDaysInYearType interestCalculationDaysInYearType;
    private double minRequiredOpeningBalance;
    private boolean withdrawalFeeForTransfers;
    private boolean allowOverdraft;
    private boolean enforceMinRequiredBalance;
    private List<Integer> startInterestCalculationFromDate;
    private boolean withHoldTax;
    private List<Integer> lastActivityDate;
    private boolean isDormancyTrackingActive;
    private Summary summary;
    private List<SavingsAccountsTransactions> transactions;
    private boolean calculateInterestDuringDormancy;
    private double maximumWithdrawableAmount;
    private int officeId;
    private String officeName;
    private String originChannel;
    private boolean depositLock;
    private boolean withdrawalLock;
}
