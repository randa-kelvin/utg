package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CapacityAssessmentMicro extends AbstractEntity {
    @Column(nullable = false, name = "loan_id")
    private String loanId;

    @Column(name = "business_address")
    private String businessAddress;

    @Column(name = "monthly_sales")
    private double monthlySales;

    @Column(name = "monthly_purchases")
    private double monthlyPurchases;

    @Column(name = "operational_expenses")
    private double operationalExpenses;

    @Column(name = "other_net_income")
    private double otherNetIncome;

    @Column(name = "family_expenses")
    private double familyExpenses;

    @Column(nullable = false, name="cash")
    private double cash;

    @Column(nullable = false, name = "inventory")
    private double inventory;

    @Column(name = "fixed_assets")
    private double fixedAssets;

    @Column(nullable = false, name = "liabilities")
    private double liabilities;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public double getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(double monthlySales) {
        this.monthlySales = monthlySales;
    }

    public double getMonthlyPurchases() {
        return monthlyPurchases;
    }

    public void setMonthlyPurchases(double monthlyPurchases) {
        this.monthlyPurchases = monthlyPurchases;
    }

    public double getOperationalExpenses() {
        return operationalExpenses;
    }

    public void setOperationalExpenses(double operationalExpenses) {
        this.operationalExpenses = operationalExpenses;
    }

    public double getOtherNetIncome() {
        return otherNetIncome;
    }

    public void setOtherNetIncome(double otherNetIncome) {
        this.otherNetIncome = otherNetIncome;
    }

    public double getFamilyExpenses() {
        return familyExpenses;
    }

    public void setFamilyExpenses(double familyExpenses) {
        this.familyExpenses = familyExpenses;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    public double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public double getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(double liabilities) {
        this.liabilities = liabilities;
    }
}
