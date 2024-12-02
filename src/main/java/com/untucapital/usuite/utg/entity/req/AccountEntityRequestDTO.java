package com.untucapital.usuite.utg.entity.req;

import lombok.Data;

import javax.persistence.*;

@Data
public class AccountEntityRequestDTO {

    private Integer accountLink;
    private String masterSubAccount;
    private Integer accountLevel;
    private String account;
    private Integer iAccountType;
    private Integer subAccOfLink;
    private String dept;
    private String brch;
    private Boolean jr;
    private String description;
    private String caseAcc;
    private Boolean activeAccount;
    private String dAccountsTimeStamp;
    private String cNextChequeNum;
    private Integer iGLSegment0ID;
    private Integer iGLSegment1ID;
    private Integer iGLSegment2ID;
    private Integer iGLSegment3ID;
    private Integer iGLSegment4ID;
    private Integer iGLSegment5ID;
    private Integer iGLSegment6ID;
    private Integer iGLSegment7ID;
    private Integer iGLSegment8ID;
    private Integer iGLSegment9ID;
    private Integer iReportCategoryID;
    private Float fBankStatementBalance;
    private String cExtDescription;
    private Integer iTaxTypeINVID;
    private Integer iTaxTypeCRNID;
    private Integer iTaxTypeGRVID;
    private Integer iTaxTypeRTSID;
    private Boolean iAllowICSales;
    private Boolean iAllowICPurchases;
    private Integer iMBReportingCategoryID;
    private Integer iMBCashFlowCategoryID;
    private Boolean bMBIsAsset;
    private Boolean bMBIsGrant;
    private Integer iMBAssetClassificationID;
    private Integer iMBAssetCategoryID;
    private Integer iMBAssetTypeID;
    private Integer iMBGrantLevel1TypeID;
    private Integer iMBGrantLevel2TypeID;
    private Integer iMBGrantLevel3TypeID;
    private Boolean bIsBranchLoanAccount;
    private Integer iForeignBankCurrencyID;
    private Integer iForeignBankPEXAccID;
    private Integer iForeignBankLEXAccID;
    private Boolean bRevalueWithSellingRate;
    private Boolean bPaymentsBasedTax;
    private String cBankName;
    private String cBankAccountName;
    private String cBankCode;
    private String cBankAccountNumber;
    private String cBranchName;
    private String cSEPABranchCode;
    private String cBankRefNr;
    private Integer iAttributeGroupID;
    private String xAttribute;
    private Boolean bForeignBankAcc;
    private Integer accountsIBranchID;
    private String accountsDCreatedDate;
    private String accountsDModifiedDate;
    private Integer accountsICreatedBranchID;
    private Integer accountsIModifiedBranchID;
    private Integer accountsICreatedAgentID;
    private Integer accountsIModifiedAgentID;
    private Integer accountsIChangeSetID;
    private byte[] accountsChecksum;
    private String cSBFBankAccountID;
    private String ulGLSector1;
    private String ulGLSector2;
    private String ulGLSector3;
    private String ulGLSector4;
    private String ulGLSector5;
    private String ulGLSector6;
    private String ulGLSector7;
    private String ulGLSector8;
    private String ulGLSector9;
    private String ulGLSector10;
    private String ulGLCostCentre;

}

