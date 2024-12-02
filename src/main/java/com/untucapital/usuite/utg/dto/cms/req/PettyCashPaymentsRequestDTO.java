package com.untucapital.usuite.utg.dto.cms.req;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

public class PettyCashPaymentsRequestDTO extends AbstractEntityDTO {

    private String purchaseOrderNumber;
    private String name;
    private String requesitionName;
    private String date;
    private String amount;
    private String count;
    private String fromAccount;
    private String toAccount;
    private String transType;
    private String firstApprover;
    private String secondApprover;
    private String status;
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
