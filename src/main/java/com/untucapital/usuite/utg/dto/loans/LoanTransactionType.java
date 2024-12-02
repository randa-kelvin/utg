package com.untucapital.usuite.utg.dto.loans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoanTransactionType {

    private int id;
    private String code;
    private String value;
    private boolean disbursement;
    private boolean repaymentAtDisbursement;
    private boolean repayment;
    private boolean contra;
    private boolean waiveInterest;
    private boolean waiveCharges;
    private boolean accrual;
    private boolean writeOff;
    private boolean recoveryRepayment;
    private boolean initiateTransfer;
    private boolean approveTransfer;
    private boolean withdrawTransfer;
    private boolean rejectTransfer;
    private boolean chargePayment;
    private boolean refund;
    private boolean refundForActiveLoans;
    private boolean suspendedIncome;
    private boolean reverseSuspendedIncome;

}
