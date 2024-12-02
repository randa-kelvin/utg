package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "musoni_loans")
public class MusoniLoan extends AbstractEntity{

    @NotNull
    @Column(name = "account_no", nullable = false)
    private String accountNo;

    private String status;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "loan_officer", nullable = false)
    private String loanOfficer;

    @Column(name = "total_due", nullable = false)
    private String totalDue;

    @Column(name = "total_paid", nullable = false)
    private String totalPaid;

    @Column(name = "total_outstanding", nullable = false)
    private String totalOutstanding;
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLoanOfficer() {
        return loanOfficer;
    }

    public void setLoanOfficer(String loanOfficer) {
        this.loanOfficer = loanOfficer;
    }

    public String getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(String totalDue) {
        this.totalDue = totalDue;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getTotalOutstanding() {
        return totalOutstanding;
    }

    public void setTotalOutstanding(String totalOutstanding) {
        this.totalOutstanding = totalOutstanding;
    }
}
