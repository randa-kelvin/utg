package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BusinessAndPersonalReferenceResponseDTO extends AbstractEntityDTO {

    private String loanId;
    private String relation;
    private String fullName;
    private String workingAddress;
    private String phone;
    private String comments;

    public BusinessAndPersonalReferenceResponseDTO(String loanId, String relation, String fullName, String workingAddress, String phone, String comments) {
        this.loanId = loanId;
        this.relation = relation;
        this.fullName = fullName;
        this.workingAddress = workingAddress;
        this.phone = phone;
        this.comments = comments;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkingAddress() {
        return workingAddress;
    }

    public void setWorkingAddress(String workingAddress) {
        this.workingAddress = workingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "BusinessAndPersonalReference{" +
                "loanId='" + loanId + '\'' +
                ", relation='" + relation + '\'' +
                ", fullName='" + fullName + '\'' +
                ", workingAddress='" + workingAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
