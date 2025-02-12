package com.untucapital.usuite.utg.model;

import com.untucapital.usuite.utg.model.fcb.Response;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
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


    @OneToOne
    @JoinColumn(name = "fcb_response_id")
    private Response fcbResponse;


//    public String getPhotoUpload() {
//        return photoUpload;
//    }
//
//    public void setPhotoUpload(String photoUpload) { this.photoUpload = photoUpload;}


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
