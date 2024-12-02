package com.untucapital.usuite.utg.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.sql.Date;

@Data
@RequiredArgsConstructor
public class PostGLResponseDTO {
    private BigInteger autoIdx;
    private Date txDate;
    private String id;
    private Integer accountLink;
    private Integer trCodeID;
    private Float debit;
    private Float credit;
    private Integer iCurrencyID;
    private Float fExchangeRate;
    private Float fForeignDebit;
    private Float fForeignCredit;
    private String description;
    private Integer taxTypeID;
    private String Reference;
    private String orderNo;
    private String extOrderNum;
    private String cAuditNumber;
    private Float taxAmount;
    private Float fForeignTax;
    private Integer project;
    private Integer period;
    private Integer drCrAccount;
    private Integer jobCodeLink;
    private Float crcCheck;
    private Date dtStamp;
    private String userName;
    private Integer iTaxPeriodID;
    private String cPayeeName;
    private Boolean bPrintCheque;
    private String cReference2;
    private Integer repID;
    private Float fJCRepCost;
    private Integer iMFPID;
    private Boolean bIsJCDocLine;
    private Boolean bIsSTGLDocLine;
    private Long iInvLineID;
    private Integer iTxBranchID;
    private String cBankRef;
    private Boolean bPBTPaid;
    private Integer iGLTaxAccountID;
    private Boolean bReconciled;
    private String xAttribute;
    private Integer postGLIBranchID;
    private Date postGLCreatedDate;
    private Date postGLModifiedDate;
    private Integer postGLICreatedBranchID;
    private Integer postGLIModifiedBranchID;
    private Integer postGLICreatedAgentID;
    private Integer postGLIModifiedAgentID;
    private Integer postGLIChangeSetID;
    private byte[] postGLChecksum;
    private Integer iMajorIndustryCodeID;
    private Integer iImportDeclarationID;
    private String cHash;
    private Integer iKeyVersion;
}
