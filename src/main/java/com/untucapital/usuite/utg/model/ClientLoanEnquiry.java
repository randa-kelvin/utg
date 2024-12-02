package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client_loans_enquiry")
public class ClientLoanEnquiry extends AbstractEntity {

    @NotNull
    @JoinColumn(name = "user_id")
    private String userId;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "industry_sector")
    private String industrySector;

    @Column(name = "business_start_date")
    private String businessStartDate;

    @Column(name = "sales_description")
    private String salesDescription;

    @Column(name = "expenses_description")
    private String expensesDescription;

    @Column(name = "securities")
    private String securities;

    @Column(name = "loan_reason")
    private String loanReason;

    @Column(name = "debts")
    private String debts;

    @Column(name = "total_assets")
    private String totalAssets;

    @Column(name = "average_profits")
    private String averageProfits;

    @Column(name = "loan_amount_needed")
    private String loanAmountNeeded;

    @Column(name = "loan_payback_period")
    private String loanPayBackPeriod;

    @Column(name = "location")
    private String location;

    @Column(name = "enquiry_status")
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
