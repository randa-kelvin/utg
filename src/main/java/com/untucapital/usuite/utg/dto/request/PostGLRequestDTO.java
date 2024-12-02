package com.untucapital.usuite.utg.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.sql.Date;

@Data
@RequiredArgsConstructor
public class PostGLRequestDTO {

    private BigInteger AutoIdx;
    private Date TxDate;
    private String Id;
    private Integer AccountLink;
    private Integer TrCodeID;
    private Float Debit;
    private Float Credit;
    private Integer ICurrencyID;
    private Float FExchangeRate;
    private Float FForeignDebit;
    private Float FForeignCredit;
    private String Description;
    private Integer TaxTypeID;
    private String Reference;
    private String Order_No;
    private String ExtOrderNum;
    private String CAuditNumber;
    private Float Tax_Amount;
    private Float FForeignTax;
    private Integer Project;
    private Integer Period;
    private Integer DrCrAccount;
    private Integer JobCodeLink;
    private Float CRCCheck;
    private Date DTStamp;
    private String UserName;
    private Integer ITaxPeriodID;
    private String CPayeeName;
    private Boolean BPrintCheque;
    private String CReference2;
    private Integer RepID;
    private Float FJCRepCost;
    private Integer IMFPID;
    private Boolean BIsJCDocLine;
    private Boolean BIsSTGLDocLine;
    private Long IInvLineID;
    private Integer ITxBranchID;
    private String CBankRef;
    private Boolean BPBTPaid;
    private Integer IGLTaxAccountID;
    private Boolean BReconciled;
    private String XAttribute;
    private Integer PostGL_iBranchID;
    private Date PostGL_dCreatedDate;
    private Date PostGL_dModifiedDate;
    private Integer PostGL_iCreatedBranchID;
    private Integer PostGL_iModifiedBranchID;
    private Integer PostGL_iCreatedAgentID;
    private Integer PostGL_iModifiedAgentID;
    private Integer PostGL_iChangeSetID;
    private byte[] PostGL_Checksum;
    private Integer iMajorIndustryCodeID;
    private Integer iImportDeclarationID;
    private String cHash;
    private Integer iKeyVersion;
}
