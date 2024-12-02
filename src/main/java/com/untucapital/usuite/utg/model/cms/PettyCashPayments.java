package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "pettyCashPayments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"purchase_order_number"})
})
public class PettyCashPayments extends AbstractEntity {

    @Column(name = "purchase_order_number")
    private String purchaseOrderNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "requesition_name")
    private String requesitionName;

    @Column(name = "date")
    private String date;

    @Column(name = "amount")
    private String amount;

    @Column(name = "count")
    private String count;

    @Column(name = "from_account")
    private String fromAccount;

    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "trans_type")
    private String transType;

    @Column(name = "first_approver")
    private String firstApprover;

    @Column(name = "second_approver")
    private String secondApprover;

    @Column(name = "status")
    private String status;

    @Column(name = "notes")
    private String notes;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRequesitionName() {
        return requesitionName;
    }

    public void setRequesitionName(String requesitionName) {
        this.requesitionName = requesitionName;
    }

    public String getFirstApprover() {
        return firstApprover;
    }

    public void setFirstApprover(String firstApprover) {
        this.firstApprover = firstApprover;
    }

    public String getSecondApprover() {
        return secondApprover;
    }

    public void setSecondApprover(String secondApprover) {
        this.secondApprover = secondApprover;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
}
