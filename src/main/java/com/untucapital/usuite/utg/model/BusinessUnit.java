package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "businessUnit")
public class BusinessUnit extends AbstractEntity {

    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    public BusinessUnit() {
    }

    public BusinessUnit(String loanId, String name, String address) {
        this.loanId = loanId;
        this.name = name;
        this.address = address;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BusinessUnit{" +
                "loanId='" + loanId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
