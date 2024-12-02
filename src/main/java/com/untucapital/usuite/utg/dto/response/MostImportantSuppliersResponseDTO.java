package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class MostImportantSuppliersResponseDTO extends AbstractEntityDTO {


    private String loanId;

    private String supplier;

    private String contact;

    private double cash;

    private double credit;
    private double monthlyPurchase;

    private String supplierRelationship;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getMonthlyPurchase() {
        return monthlyPurchase;
    }

    public void setMonthlyPurchase(double monthlyPurchase) {
        this.monthlyPurchase = monthlyPurchase;
    }

    public String getSupplierRelationship() {
        return supplierRelationship;
    }

    public void setSupplierRelationship(String supplierRelationship) {
        this.supplierRelationship = supplierRelationship;
    }
}
