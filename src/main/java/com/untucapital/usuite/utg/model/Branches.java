package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "branches", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"branch_name", "code"})
})
public class Branches extends AbstractEntity {

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "branch_tel_phone")
    private String branchTellPhone;

    @Column(name = "branch_status")
    private String branchStatus;

    @Column(name = "vault_account_number")
    private String vaultAccountNumber;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "google_map")
    private String googleMap;

    @Column(name = "directions_link")
    private String directionsLink;

    @Column(name = "code")
    private int code;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchTellPhone() {
        return branchTellPhone;
    }

    public void setBranchTellPhone(String branchTellPhone) {
        this.branchTellPhone = branchTellPhone;
    }

    public String getBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(String branchStatus) {
        this.branchStatus = branchStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getGoogleMap() {
        return googleMap;
    }

    public void setGoogleMap(String googleMap) {
        this.googleMap = googleMap;
    }

    public String getVaultAccountNumber() {
        return vaultAccountNumber;
    }

    public void setVaultAccountNumber(String vaultAccountNumber) {
        this.vaultAccountNumber = vaultAccountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getDirectionsLink() {
        return directionsLink;
    }

    public void setDirectionsLink(String directionsLink) {
        this.directionsLink = directionsLink;
    }
}
