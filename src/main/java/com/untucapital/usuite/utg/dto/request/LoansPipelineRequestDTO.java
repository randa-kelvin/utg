package com.untucapital.usuite.utg.dto.request;


public class LoansPipelineRequestDTO {

    private Long intId;

    private String userId;

    private String branchName;

    private String dateRecorded;

    private String applicant;

    private String sector;

    private String repeatClient;

    private Double soughtLoan;

    private String loanStatus;

    private String loanOfficer;

    public Long getIntId() {
        return intId;
    }

    public void setIntId(Long intId) {
        this.intId = intId;
    }

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
}
