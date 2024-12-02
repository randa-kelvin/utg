package com.untucapital.usuite.utg.commons;


public interface AppConstants {

    String SEQUENCE_NAME = "transaction_voucher_sequence";
    String DISBURSEMENT = "LOA-DIS";
    String REPAYMENT = "LOA-REP";
    String CASH = "CAS-TRN";
    String CURRENCY = "001";
    String VAULT_TYPE ="Petty Cash";
    Integer LOAN_BOOK = 1010;
    String CURRENCY_USD = "USD";

    Integer LOAN_BOOK_ACCOUNT_DIS = 2248;
    Integer LOAN_BOOK_ACCOUNT_REP = 2247;
    String LOAN_BOOK_ACCOUNT_NAME_DIS = "8000/000/HO/LD";
    String LOAN_BOOK_ACCOUNT_NAME_REP = "8000/000/HO/LR";
    String LOAN_BOOK_ACCOUNT_NAME = "9000/000";
    String HRE = "Harare Petty Cash";
    String BYO = "Bulawayo Petty Cash";
    String GWR = "Gweru Petty Cash";
    String GKW = "Gokwe Petty Cash";
    String HO = "Head Office Petty Cash";

    Integer EXCHANGE_RATE = 1;

//    RESPONSE MESSAGES:
    Integer NOT_FOUND = 404;

    String BALANCE_BD = "Balance b/d";
    String FEE_APPLIED = "Fee Applied";
    String PENATLY_FEE = "5% Penalty fee applied for late repayment";
    String INTEREST_APPLIED = "Interest Applied";
    String LOAN_DISBURSEMENT = "Disbursement";
    String LOAN_REPAYMENT = "Repayment";
    String LOAN_DEPOSIT = "Deposit";
    String LOAN_ACCRUAL = "Accrual";

}
