package com.untucapital.usuite.utg.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountLink")
    private Integer accountLink;

    @Column(name = "Master_Sub_Account")
    private String masterSubAccount;

    @Column(name = "AccountLevel")
    private Integer accountLevel;

    @Column(name = "Account")
    private String account;

    @Column(name = "iAccountType")
    private Integer iAccountType;

    @Column(name = "SubAccOfLink")
    private Integer subAccOfLink;

    @Column(name = "Dept")
    private String dept;

    @Column(name = "Brch")
    private String brch;

    @Column(name = "Jr")
    private Boolean jr;

    @Column(name = "Description")
    private String description;

    @Column(name = "CaseAcc")
    private String caseAcc;

    @Column(name = "ActiveAccount")
    private Boolean activeAccount;

    @Column(name = "dAccountsTimeStamp")
    private String dAccountsTimeStamp;

    @Column(name = "cNextChequeNum")
    private String cNextChequeNum;

    @Column(name = "iGLSegment0ID")
    private Integer iGLSegment0ID;

    @Column(name = "iGLSegment1ID")
    private Integer iGLSegment1ID;

    @Column(name = "iGLSegment2ID")
    private Integer iGLSegment2ID;

    @Column(name = "iGLSegment3ID")
    private Integer iGLSegment3ID;

    @Column(name = "iGLSegment4ID")
    private Integer iGLSegment4ID;

    @Column(name = "iGLSegment5ID")
    private Integer iGLSegment5ID;

    @Column(name = "iGLSegment6ID")
    private Integer iGLSegment6ID;

    @Column(name = "iGLSegment7ID")
    private Integer iGLSegment7ID;

    @Column(name = "iGLSegment8ID")
    private Integer iGLSegment8ID;

    @Column(name = "iGLSegment9ID")
    private Integer iGLSegment9ID;

    @Column(name = "iReportCategoryID")
    private Integer iReportCategoryID;

    @Column(name = "fBankStatementBalance")
    private Float fBankStatementBalance;

    @Column(name = "cExtDescription")
    private String cExtDescription;

    @Column(name = "iTaxTypeINVID")
    private Integer iTaxTypeINVID;

    @Column(name = "iTaxTypeCRNID")
    private Integer iTaxTypeCRNID;

    @Column(name = "iTaxTypeGRVID")
    private Integer iTaxTypeGRVID;

    @Column(name = "iTaxTypeRTSID")
    private Integer iTaxTypeRTSID;

    @Column(name = "iAllowICSales")
    private Boolean iAllowICSales;

    @Column(name = "iAllowICPurchases")
    private Boolean iAllowICPurchases;

    @Column(name = "iMBReportingCategoryID")
    private Integer iMBReportingCategoryID;

    @Column(name = "iMBCashFlowCategoryID")
    private Integer iMBCashFlowCategoryID;

    @Column(name = "bMBIsAsset")
    private Boolean bMBIsAsset;

    @Column(name = "bMBIsGrant")
    private Boolean bMBIsGrant;

    @Column(name = "iMBAssetClassificationID")
    private Integer iMBAssetClassificationID;

    @Column(name = "iMBAssetCategoryID")
    private Integer iMBAssetCategoryID;

    @Column(name = "iMBAssetTypeID")
    private Integer iMBAssetTypeID;

    @Column(name = "iMBGrantLevel1TypeID")
    private Integer iMBGrantLevel1TypeID;

    @Column(name = "iMBGrantLevel2TypeID")
    private Integer iMBGrantLevel2TypeID;

    @Column(name = "iMBGrantLevel3TypeID")
    private Integer iMBGrantLevel3TypeID;

    @Column(name = "bIsBranchLoanAccount")
    private Boolean bIsBranchLoanAccount;

    @Column(name = "iForeignBankCurrencyID")
    private Integer iForeignBankCurrencyID;

    @Column(name = "iForeignBankPEXAccID")
    private Integer iForeignBankPEXAccID;

    @Column(name = "iForeignBankLEXAccID")
    private Integer iForeignBankLEXAccID;

    @Column(name = "bRevalueWithSellingRate")
    private Boolean bRevalueWithSellingRate;

    @Column(name = "bPaymentsBasedTax")
    private Boolean bPaymentsBasedTax;

    @Column(name = "cBankName")
    private String cBankName;

    @Column(name = "cBankAccountName")
    private String cBankAccountName;

    @Column(name = "cBankCode")
    private String cBankCode;

    @Column(name = "cBankAccountNumber")
    private String cBankAccountNumber;

    @Column(name = "cBranchName")
    private String cBranchName;

    @Column(name = "cSEPABranchCode")
    private String cSEPABranchCode;

    @Column(name = "cBankRefNr")
    private String cBankRefNr;

    @Column(name = "iAttributeGroupID")
    private Integer iAttributeGroupID;

    @Column(name = "xAttribute")
    private String xAttribute;

    @Column(name = "bForeignBankAcc")
    private Boolean bForeignBankAcc;

    @Column(name = "Accounts_iBranchID")
    private Integer accountsIBranchID;

    @Column(name = "Accounts_dCreatedDate")
    private String accountsDCreatedDate;

    @Column(name = "Accounts_dModifiedDate")
    private String accountsDModifiedDate;

    @Column(name = "Accounts_iCreatedBranchID")
    private Integer accountsICreatedBranchID;

    @Column(name = "Accounts_iModifiedBranchID")
    private Integer accountsIModifiedBranchID;

    @Column(name = "Accounts_iCreatedAgentID")
    private Integer accountsICreatedAgentID;

    @Column(name = "Accounts_iModifiedAgentID")
    private Integer accountsIModifiedAgentID;

    @Column(name = "Accounts_iChangeSetID")
    private Integer accountsIChangeSetID;

    @Column(name = "Accounts_Checksum")
    private byte[] accountsChecksum;

    @Column(name = "cSBFBankAccountID")
    private String cSBFBankAccountID;

    @Column(name = "ulGLSector1")
    private String ulGLSector1;

    @Column(name = "ulGLSector2")
    private String ulGLSector2;

    @Column(name = "ulGLSector3")
    private String ulGLSector3;

    @Column(name = "ulGLSector4")
    private String ulGLSector4;

    @Column(name = "ulGLSector5")
    private String ulGLSector5;

    @Column(name = "ulGLSector6")
    private String ulGLSector6;

    @Column(name = "ulGLSector7")
    private String ulGLSector7;

    @Column(name = "ulGLSector8")
    private String ulGLSector8;

    @Column(name = "ulGLSector9")
    private String ulGLSector9;

    @Column(name = "ulGLSector10")
    private String ulGLSector10;

    @Column(name = "ulGLCostCentre")
    private String ulGLCostCentre;

}

