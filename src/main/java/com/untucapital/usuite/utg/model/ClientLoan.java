package com.untucapital.usuite.utg.model;

import com.untucapital.usuite.utg.model.fcb.Response;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "client_loans")
public class ClientLoan extends AbstractEntity {


    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @Column(name = "marital_status", nullable = false)
    private String maritalStatus;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "place_of_business", nullable = false)
    private String placeOfBusiness;

    @Column(name = "industry_code", nullable = false)
    private String industryCode;

    @Column(name = "loan_amount", nullable = false)
    private String loanAmount;

    @Column(name = "street_no", nullable = false)
    private String streetNo;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "business_start_date", nullable = false)
    private String businessStartDate;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "tenure", nullable = false)
    private String tenure;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "loan_status", nullable = false)
    private String loanStatus;

    @Column(name = "loan_status_assigner")
    private String loanStatusAssigner;

    @Column(name = "fcb_score")
    private Integer fcbScore;

    @Column(name = "fcb_status")
    private String fcbStatus;

    @Column(name = "comment")
    private String comment;

    @Column(name = "assign_to", length = 80)
    private String assignTo;

    @Column(name = "assigned_by", length = 80)
    private String assignedBy;

    @Column(name = "additional_remarks")
    private String additionalRemarks;

    @Column(name = "loan_file_id", length = 80)
    private String loanFileId;

    @Column(name = "process_loan_status", length = 80)
    private String processLoanStatus;

    @Column(name = "processed_by", length = 80)
    private String processedBy;

    @Column(name = "meeting_loan_amount", length = 80)
    private String meetingLoanAmount;

    @Column(name = "meeting_tenure")
    private String meetingTenure;

    @Column(name = "meeting_interest_rate", length = 20)
    private String meetingInterestRate;

    @Column(name = "meeting_on_which_basis", length = 20)
    private String meetingOnWhichBasis;

    @Column(name = "meeting_cash_handling_fee", length = 20)
    private String meetingCashHandlingFee;

    @Column(name = "meeting_repayment_amount")
    private String meetingRepaymentAmount;

    @Column(name = "meeting_product", length = 20)
    private String meetingProduct;

    @Column(name = "meeting_rn", length = 20)
    private String meetingRN;

    @Column(name = "meeting_upfront_fee", length = 20)
    private String meetingUpfrontFee;

    @Column(name = "meeting_finalized_by")
    private String meetingFinalizedBy;

    @Column(name = "boco_signature")
    private String bocoSignature;

    @Column(name = "boco_signature_image")
    private String bocoSignatureImage;

    @Column(name = "boco_name")
    private String bocoName;

    @Column(name = "bm_signature")
    private String bmSignature;

    @Column(name = "bm_name")
    private String bmName;

    @Column(name = "ca_signature")
    private String caSignature;

    @Column(name = "ca_name")
    private String caName;

    @Column(name = "cm_signature")
    private String cmSignature;

    @Column(name = "cm_name")
    private String cmName;

    @Column(name = "fin_signature")
    private String finSignature;

    @Column(name = "fin_name")
    private String finName;

    @Column(name = "board_signature")
    private String boardSignature;

    @Column(name = "board_name")
    private String boardName;

    @Column(name = "assigned_status")
    private String assignedStatus;

    @Column(name = "less_fees", length = 20)
    private String lessFees;

    @Column(name = "application_fee", length = 20)
    private String applicationFee;

    @Column(name = "boco_date")
    private String bocoDate;

    @Column(name = "bm_date_assign_lo")
    private String bmDateAssignLo;

    @Column(name = "lo_date")
    private String loDate;

    @Column(name = "bm_date_meeting")
    private String bmDateMeeting;

    @Column(name = "cc_date")
    private String ccDate;

    @Column(name = "predis_date")
    private String predisDate;

    @Column(name = "pipeline_status", length = 40)
    private String pipelineStatus;

    @Column(name = "bm_set_meeting")
    private String bmSetMeeting;

    @Column(name = "credit_commit")
    private String creditCommit;

    @Column(name = "completely_done", length = 40)
    private String completelyDone;

    @Column(name = "next_of_kin_name", length = 80)
    private String nextOfKinName;

    @Column(name = "next_of_kin_phone", length = 20)
    private String nextOfKinPhone;

    @Column(name = "next_of_kin_relationship")
    private String nextOfKinRelationship;

    @Column(name = "next_of_kin_address")
    private String nextOfKinAddress;

    @Column(name = "next_of_kin_name2")
    private String nextOfKinName2;

    @Column(name = "next_of_kin_phone2", length = 20)
    private String nextOfKinPhone2;

    @Column(name = "next_of_kin_relationship2")
    private String nextOfKinRelationship2;

    @Column(name = "next_of_kin_address2")
    private String nextOfKinAddress2;

    @Column(name = "client_loan_id")
    private String clientLoanId;

    @Column(name = "loan_count")
    private String loanCount;

    @Column(name = "platform_used")
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
    @JoinColumn(name = "fcb_response_id")
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

    @Override
    public String toString() {
        return "ClientLoan{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", branchName='" + branchName + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", placeOfBusiness='" + placeOfBusiness + '\'' +
                ", industryCode='" + industryCode + '\'' +
                ", loanAmount='" + loanAmount + '\'' +
                ", streetNo='" + streetNo + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessStartDate='" + businessStartDate + '\'' +
                ", streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", tenure='" + tenure + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", loanStatusAssigner='" + loanStatusAssigner + '\'' +
                ", fcbScore=" + fcbScore +
                ", fcbStatus='" + fcbStatus + '\'' +
                ", comment='" + comment + '\'' +
                ", assignTo='" + assignTo + '\'' +
                ", assignedBy='" + assignedBy + '\'' +
                ", additionalRemarks='" + additionalRemarks + '\'' +
                ", loanFileId='" + loanFileId + '\'' +
                ", processLoanStatus='" + processLoanStatus + '\'' +
                ", processedBy='" + processedBy + '\'' +
                ", meetingLoanAmount='" + meetingLoanAmount + '\'' +
                ", meetingTenure='" + meetingTenure + '\'' +
                ", meetingInterestRate='" + meetingInterestRate + '\'' +
                ", meetingOnWhichBasis='" + meetingOnWhichBasis + '\'' +
                ", meetingCashHandlingFee='" + meetingCashHandlingFee + '\'' +
                ", meetingRepaymentAmount='" + meetingRepaymentAmount + '\'' +
                ", meetingProduct='" + meetingProduct + '\'' +
                ", meetingRN='" + meetingRN + '\'' +
                ", meetingUpfrontFee='" + meetingUpfrontFee + '\'' +
                ", meetingFinalizedBy='" + meetingFinalizedBy + '\'' +
                ", bocoSignature='" + bocoSignature + '\'' +
                ", bocoSignatureImage='" + bocoSignatureImage + '\'' +
                ", bocoName='" + bocoName + '\'' +
                ", bmSignature='" + bmSignature + '\'' +
                ", bmName='" + bmName + '\'' +
                ", caSignature='" + caSignature + '\'' +
                ", caName='" + caName + '\'' +
                ", cmSignature='" + cmSignature + '\'' +
                ", cmName='" + cmName + '\'' +
                ", finSignature='" + finSignature + '\'' +
                ", finName='" + finName + '\'' +
                ", boardSignature='" + boardSignature + '\'' +
                ", boardName='" + boardName + '\'' +
                ", assignedStatus='" + assignedStatus + '\'' +
                ", lessFees='" + lessFees + '\'' +
                ", applicationFee='" + applicationFee + '\'' +
                ", bocoDate='" + bocoDate + '\'' +
                ", bmDateAssignLo='" + bmDateAssignLo + '\'' +
                ", loDate='" + loDate + '\'' +
                ", bmDateMeeting='" + bmDateMeeting + '\'' +
                ", ccDate='" + ccDate + '\'' +
                ", predisDate='" + predisDate + '\'' +
                ", pipelineStatus='" + pipelineStatus + '\'' +
                ", bmSetMeeting='" + bmSetMeeting + '\'' +
                ", creditCommit='" + creditCommit + '\'' +
                ", completelyDone='" + completelyDone + '\'' +
                ", nextOfKinName='" + nextOfKinName + '\'' +
                ", nextOfKinPhone='" + nextOfKinPhone + '\'' +
                ", nextOfKinRelationship='" + nextOfKinRelationship + '\'' +
                ", nextOfKinAddress='" + nextOfKinAddress + '\'' +
                ", nextOfKinName2='" + nextOfKinName2 + '\'' +
                ", nextOfKinPhone2='" + nextOfKinPhone2 + '\'' +
                ", nextOfKinRelationship2='" + nextOfKinRelationship2 + '\'' +
                ", nextOfKinAddress2='" + nextOfKinAddress2 + '\'' +
                ", clientLoanId='" + clientLoanId + '\'' +
                ", loanCount='" + loanCount + '\'' +
                ", platformUsed='" + platformUsed + '\'' +
                ", fcbResponse=" + fcbResponse +
                '}';
    }
}
