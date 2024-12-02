package com.untucapital.usuite.utg.entity.req;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PostGlRequestDTO {

    private BigInteger AutoIdx;
    @Temporal(TemporalType.TIMESTAMP)
    private Date txDate;
    private String Id;
    private Integer accountLink;
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
    @Temporal(TemporalType.TIMESTAMP)
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
    @Column(columnDefinition = "XML")
    private String XAttribute;
    private Integer PostGL_iBranchID;
    @Temporal(TemporalType.TIMESTAMP)
    private Date PostGL_dCreatedDate;
    @Temporal(TemporalType.TIMESTAMP)
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
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PostGlRequestDTO postGl = (PostGlRequestDTO) o;
        return getAutoIdx() != null && Objects.equals(getAutoIdx(), postGl.getAutoIdx());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
