package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.model.AbstractEntity;
import com.untucapital.usuite.utg.model.fcb.Response;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ClientLoanResponseDTO extends AbstractEntity {


    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String idNumber;
    private String branchName;
    private String maritalStatus;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String placeOfBusiness;
    private String industryCode;
    private String loanAmount;
    private String streetNo;
    private String businessName;
    private String businessStartDate;
    private String streetName;
    private String suburb;
    private String city;
    private String tenure;
    private String loanStatus;
    private String loanStatusAssigner;
    private Integer fcbScore;
    private String fcbStatus;
    private String comment;
    private String assignTo;
    private String assignedBy;
    private String additionalRemarks;
    private String loanFileId;
    private String processLoanStatus;
    private String processedBy;
    private String meetingLoanAmount;
    private String meetingTenure;
    private String meetingInterestRate;
    private String meetingOnWhichBasis;
    private String meetingCashHandlingFee;
    private String meetingRepaymentAmount;
    private String meetingProduct;
    private String meetingRN;
    private String meetingUpfrontFee;
    private String meetingFinalizedBy;
    private String bocoSignature;
    private String bocoSignatureImage;
    private String bocoName;
    private String bmSignature;
    private String bmName;
    private String caSignature;
    private String caName;
    private String cmSignature;
    private String cmName;
    private String finSignature;
    private String finName;
    private String boardSignature;
    private String boardName;
    private String assignedStatus;
    private String lessFees;
    private String applicationFee;
    private String bocoDate;
    private String bmDateAssignLo;
    private String loDate;
    private String bmDateMeeting;
    private String ccDate;
    private String predisDate;
    private String pipelineStatus;
    private String bmSetMeeting;
    private String creditCommit;
    private String completelyDone;
    private String nextOfKinName;
    private String nextOfKinPhone;
    private String nextOfKinRelationship;
    private String nextOfKinAddress;
    private String nextOfKinName2;
    private String nextOfKinPhone2;
    private String nextOfKinRelationship2;
    private String nextOfKinAddress2;
    private String clientLoanId;
    private String loanCount;
    private String platformUsed;

    public String getBmSetMeeting() {
        return bmSetMeeting;
    }

    public void setBmSetMeeting(String bmSetMeeting) {
        this.bmSetMeeting = bmSetMeeting;
    }

    public String getProcessLoanStatus() {
        return processLoanStatus;
    }

    public void setProcessLoanStatus(String processLoanStatus) {
        this.processLoanStatus = processLoanStatus;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }

    @OneToOne
    @NotNull
    @JoinColumn(name = "fcb_response_id", nullable = false)
    private Response fcbResponse;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }


//    public String getPhotoUpload() {
//        return photoUpload;
//    }
//
//    public void setPhotoUpload(String photoUpload) { this.photoUpload = photoUpload;}

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlaceOfBusiness() {
        return placeOfBusiness;
    }

    public void setPlaceOfBusiness(String placeOfBusiness) {
        this.placeOfBusiness = placeOfBusiness;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTenure() {
        return tenure;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Integer getFcbScore() {
        return fcbScore;
    }

    public void setFcbScore(Integer fcbScore) {
        this.fcbScore = fcbScore;
    }

    public String getFcbStatus() {
        return fcbStatus;
    }

    public void setFcbStatus(String fcbStatus) {
        this.fcbStatus = fcbStatus;
    }

    public Response getFcbResponse() {
        return fcbResponse;
    }

    public void setFcbResponse(Response fcbResponse) {
        this.fcbResponse = fcbResponse;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getLoanStatusAssigner() {
        return loanStatusAssigner;
    }

    public void setLoanStatusAssigner(String loanStatusAssigner) {
        this.loanStatusAssigner = loanStatusAssigner;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getAdditionalRemarks() {
        return additionalRemarks;
    }

    public void setAdditionalRemarks(String additionalRemarks) {
        this.additionalRemarks = additionalRemarks;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

     public String getBusinessStartDate() {
        return businessStartDate;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBusinessStartDate(String businessStartDate) {
        this.businessStartDate = businessStartDate;
    }

    public String getLoanFileId() {
        return loanFileId;
    }

    public void setLoanFileId(String loanFileId) {
        this.loanFileId = loanFileId;
    }

    public String getMeetingLoanAmount() {
        return meetingLoanAmount;
    }

    public void setMeetingLoanAmount(String meetingLoanAmount) {
        this.meetingLoanAmount = meetingLoanAmount;
    }

    public String getMeetingTenure() {
        return meetingTenure;
    }

    public void setMeetingTenure(String meetingTenure) {
        this.meetingTenure = meetingTenure;
    }

    public String getMeetingInterestRate() {
        return meetingInterestRate;
    }

    public void setMeetingInterestRate(String meetingInterestRate) {
        this.meetingInterestRate = meetingInterestRate;
    }

    public String getMeetingOnWhichBasis() {
        return meetingOnWhichBasis;
    }

    public void setMeetingOnWhichBasis(String meetingOnWhichBasis) {
        this.meetingOnWhichBasis = meetingOnWhichBasis;
    }

    public String getMeetingCashHandlingFee() {
        return meetingCashHandlingFee;
    }

    public void setMeetingCashHandlingFee(String meetingCashHandlingFee) {
        this.meetingCashHandlingFee = meetingCashHandlingFee;
    }

    public String getMeetingRepaymentAmount() {
        return meetingRepaymentAmount;
    }

    public void setMeetingRepaymentAmount(String meetingRepaymentAmount) {
        this.meetingRepaymentAmount = meetingRepaymentAmount;
    }

    public String getMeetingProduct() {
        return meetingProduct;
    }

    public void setMeetingProduct(String meetingProduct) {
        this.meetingProduct = meetingProduct;
    }

    public String getMeetingRN() {
        return meetingRN;
    }

    public void setMeetingRN(String meetingRN) {
        this.meetingRN = meetingRN;
    }

    public String getMeetingUpfrontFee() {
        return meetingUpfrontFee;
    }

    public void setMeetingUpfrontFee(String meetingUpfrontFee) {
        this.meetingUpfrontFee = meetingUpfrontFee;
    }

    public String getMeetingFinalizedBy() {
        return meetingFinalizedBy;
    }

    public void setMeetingFinalizedBy(String meetingFinalizedBy) {
        this.meetingFinalizedBy = meetingFinalizedBy;
    }

    public String getBocoSignature() {
        return bocoSignature;
    }

    public void setBocoSignature(String bocoSignature) {
        this.bocoSignature = bocoSignature;
    }

    public String getBmSignature() {
        return bmSignature;
    }

    public void setBmSignature(String bmSignature) {
        this.bmSignature = bmSignature;
    }

    public String getCaSignature() {
        return caSignature;
    }

    public void setCaSignature(String caSignature) {
        this.caSignature = caSignature;
    }

    public String getCmSignature() {
        return cmSignature;
    }

    public void setCmSignature(String cmSignature) {
        this.cmSignature = cmSignature;
    }

    public String getFinSignature() {
        return finSignature;
    }

    public void setFinSignature(String finSignature) {
        this.finSignature = finSignature;
    }

    public String getBocoName() {
        return bocoName;
    }

    public void setBocoName(String bocoName) {
        this.bocoName = bocoName;
    }

    public String getBmName() {
        return bmName;
    }

    public void setBmName(String bmName) {
        this.bmName = bmName;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public String getCmName() {
        return cmName;
    }

    public void setCmName(String cmName) {
        this.cmName = cmName;
    }

    public String getFinName() {
        return finName;
    }

    public void setFinName(String finName) {
        this.finName = finName;
    }

    public String getBoardSignature() {
        return boardSignature;
    }

    public void setBoardSignature(String boardSignature) {
        this.boardSignature = boardSignature;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getAssignedStatus() {
        return assignedStatus;
    }

    public void setAssignedStatus(String assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    public String getLessFees() {
        return lessFees;
    }

    public void setLessFees(String lessFees) {
        this.lessFees = lessFees;
    }

    public String getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(String applicationFee) {
        this.applicationFee = applicationFee;
    }

    public String getBocoDate() {
        return bocoDate;
    }

    public void setBocoDate(String bocoDate) {
        this.bocoDate = bocoDate;
    }

    public String getBmDateAssignLo() {
        return bmDateAssignLo;
    }

    public void setBmDateAssignLo(String bmDateAssignLo) {
        this.bmDateAssignLo = bmDateAssignLo;
    }

    public String getLoDate() {
        return loDate;
    }

    public void setLoDate(String loDate) {
        this.loDate = loDate;
    }

    public String getBmDateMeeting() {
        return bmDateMeeting;
    }

    public void setBmDateMeeting(String bmDateMeeting) {
        this.bmDateMeeting = bmDateMeeting;
    }

    public String getCcDate() {
        return ccDate;
    }

    public void setCcDate(String ccDate) {
        this.ccDate = ccDate;
    }

    public String getPredisDate() {
        return predisDate;
    }

    public void setPredisDate(String predisDate) {
        this.predisDate = predisDate;
    }

    public String getPipelineStatus() {
        return pipelineStatus;
    }

    public void setPipelineStatus(String pipelineStatus) {
        this.pipelineStatus = pipelineStatus;
    }

    public String getCreditCommit() {
        return creditCommit;
    }

    public void setCreditCommit(String creditCommit) {
        this.creditCommit = creditCommit;
    }

    public String getBocoSignatureImage() {
        return bocoSignatureImage;
    }

    public void setBocoSignatureImage(String bocoSignatureImage) {
        this.bocoSignatureImage = bocoSignatureImage;
    }

    public String getCompletelyDone() {
        return completelyDone;
    }

    public void setCompletelyDone(String completelyDone) {
        this.completelyDone = completelyDone;
    }

    public String getNextOfKinName() {
        return nextOfKinName;
    }

    public void setNextOfKinName(String nextOfKinName) {
        this.nextOfKinName = nextOfKinName;
    }

    public String getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    public void setNextOfKinPhone(String nextOfKinPhone) {
        this.nextOfKinPhone = nextOfKinPhone;
    }

    public String getNextOfKinRelationship() {
        return nextOfKinRelationship;
    }

    public void setNextOfKinRelationship(String nextOfKinRelationship) {
        this.nextOfKinRelationship = nextOfKinRelationship;
    }

    public String getNextOfKinAddress() {
        return nextOfKinAddress;
    }

    public void setNextOfKinAddress(String nextOfKinAddress) {
        this.nextOfKinAddress = nextOfKinAddress;
    }

    public String getNextOfKinName2() {
        return nextOfKinName2;
    }

    public void setNextOfKinName2(String nextOfKinName2) {
        this.nextOfKinName2 = nextOfKinName2;
    }

    public String getNextOfKinPhone2() {
        return nextOfKinPhone2;
    }

    public void setNextOfKinPhone2(String nextOfKinPhone2) {
        this.nextOfKinPhone2 = nextOfKinPhone2;
    }

    public String getNextOfKinRelationship2() {
        return nextOfKinRelationship2;
    }

    public void setNextOfKinRelationship2(String nextOfKinRelationship2) {
        this.nextOfKinRelationship2 = nextOfKinRelationship2;
    }

    public String getNextOfKinAddress2() {
        return nextOfKinAddress2;
    }

    public void setNextOfKinAddress2(String nextOfKinAddress2) {
        this.nextOfKinAddress2 = nextOfKinAddress2;
    }

    public String getClientLoanId() {
        return clientLoanId;
    }

    public void setClientLoanId(String clientLoanId) {
        this.clientLoanId = clientLoanId;
    }

    public String getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(String loanCount) {
        this.loanCount = loanCount;
    }

    public String getPlatformUsed() {
        return platformUsed;
    }

    public void setPlatformUsed(String platformUsed) {
        this.platformUsed = platformUsed;
    }
}
