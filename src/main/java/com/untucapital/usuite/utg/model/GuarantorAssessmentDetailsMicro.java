package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class GuarantorAssessmentDetailsMicro extends AbstractEntity {

    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "name")
    private String name;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "spouse")
    private String spouse;

    @Column(name = "family_members")
    private String familyMembers;

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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(String familyMembers) {
        this.familyMembers = familyMembers;
    }
}
