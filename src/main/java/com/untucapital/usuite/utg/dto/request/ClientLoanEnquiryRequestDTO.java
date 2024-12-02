package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.model.AbstractEntity;

public class ClientLoanEnquiryRequestDTO extends AbstractEntity {

    private String userId;
    private String nationalId;
    private String gender;
    private String industrySector;
    private String businessStartDate;
    private String salesDescription;
    private String expensesDescription;
    private String securities;
    private String loanReason;
    private String debts;
    private String totalAssets;
    private String averageProfits;
    private String loanAmountNeeded;
    private String loanPayBackPeriod;
    private String location;
    private String enquiryStatus;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(String industrySector) {
        this.industrySector = industrySector;
    }

    public String getBusinessStartDate() {
        return businessStartDate;
    }

    public void setBusinessStartDate(String businessStartDate) {
        this.businessStartDate = businessStartDate;
    }

    public String getSalesDescription() {
        return salesDescription;
    }

    public void setSalesDescription(String salesDescription) {
        this.salesDescription = salesDescription;
    }

    public String getExpensesDescription() {
        return expensesDescription;
    }

    public void setExpensesDescription(String expensesDescription) {
        this.expensesDescription = expensesDescription;
    }

    public String getSecurities() {
        return securities;
    }

    public void setSecurities(String securities) {
        this.securities = securities;
    }

    public String getLoanReason() {
        return loanReason;
    }

    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
    }

    public String getDebts() {
        return debts;
    }

    public void setDebts(String debts) {
        this.debts = debts;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets;
    }

    public String getAverageProfits() {
        return averageProfits;
    }

    public void setAverageProfits(String averageProfits) {
        this.averageProfits = averageProfits;
    }

    public String getLoanAmountNeeded() {
        return loanAmountNeeded;
    }

    public void setLoanAmountNeeded(String loanAmountNeeded) {
        this.loanAmountNeeded = loanAmountNeeded;
    }

    public String getLoanPayBackPeriod() {
        return loanPayBackPeriod;
    }

    public void setLoanPayBackPeriod(String loanPayBackPeriod) {
        this.loanPayBackPeriod = loanPayBackPeriod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEnquiryStatus() {
        return enquiryStatus;
    }

    public void setEnquiryStatus(String enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
    }
}
