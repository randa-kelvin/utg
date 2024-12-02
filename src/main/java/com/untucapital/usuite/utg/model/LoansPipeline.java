package com.untucapital.usuite.utg.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LoansPipeline")
public class LoansPipeline extends AbstractEntity {

//    @Id
//    @Column(name = "int_id")
//    private Long intId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "date_recorded")
    private String dateRecorded;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "sector")
    private String sector;

    @Column(name = "repeat_client")
    private String repeatClient;

    @Column(name = "sought_loan")
    private Double soughtLoan;

    @Column(name = "loan_status")
    private String loanStatus;

    @Column(name = "loan_officer")
    private String loanOfficer;

    @Column(name = "average_target")
    private  Double averageTarget;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(String dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getRepeatClient() {
        return repeatClient;
    }

    public void setRepeatClient(String repeatClient) {
        this.repeatClient = repeatClient;
    }

    public Double getSoughtLoan() {
        return soughtLoan;
    }

    public void setSoughtLoan(Double soughtLoan) {
        this.soughtLoan = soughtLoan;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getLoanOfficer() {
        return loanOfficer;
    }

    public void setLoanOfficer(String loanOfficer) {
        this.loanOfficer = loanOfficer;
    }

    public Double getAverageTarget() {
        return averageTarget;
    }

    public void setAverageTarget(Double averageTarget) {
        this.averageTarget = averageTarget;
    }
}
