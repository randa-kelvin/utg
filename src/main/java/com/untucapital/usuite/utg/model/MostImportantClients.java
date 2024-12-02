package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mostImportantClients")
public class MostImportantClients extends AbstractEntity {

    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;

    @Column(name = "cash")
    private String cash;

    @Column(name = "credit")
    private double credit;

    @Column(name = "monthly_sales")
    private double monthlySales;

    @Column(name = "relation_with_client")
    private String relationWithClient;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(double monthlySales) {
        this.monthlySales = monthlySales;
    }

    public String getRelationWithClient() {
        return relationWithClient;
    }

    public void setRelationWithClient(String relationWithClient) {
        this.relationWithClient = relationWithClient;
    }
}
