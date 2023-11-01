package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "requisitions")
public class Requisitions extends AbstractEntity {

    @NotNull
    @Column(nullable = false, name = "po_number")
    private String poNumber;

    @NotNull
    @Column(nullable = false, name = "po_name")
    private String poName;

    @Column(name = "po_total")
    private String poTotal;

    @Column(name = "po_count")
    private String poCount;

    @NotNull
    @Column(nullable = false, name = "po_status")
    private String poStatus;

    @Column(name = "notes")
    private String notes;

    @Column(name = "user_id")
    private String userId;

    @ElementCollection
    private List<String> approvers;
    @ElementCollection
    private List<String> attachments;

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public String getPoTotal() {
        return poTotal;
    }

    public void setPoTotal(String poTotal) {
        this.poTotal = poTotal;
    }

    public String getPoCount() {
        return poCount;
    }

    public void setPoCount(String poCount) {
        this.poCount = poCount;
    }

    public String getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<String> approvers) {
        this.approvers = approvers;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
