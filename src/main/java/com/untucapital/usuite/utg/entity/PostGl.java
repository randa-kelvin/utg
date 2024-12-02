package com.untucapital.usuite.utg.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "PostGL")
public class PostGl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AutoIdx")
    private BigInteger AutoIdx;

    @Column(name = "TxDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date txDate;

    @Column(name = "Id", length = 5)
    private String Id;

    @Column(name = "AccountLink")
    private Integer accountLink;

    @Column(name = "TrCodeID")
    private Integer TrCodeID;

    @Column(name = "Debit")
    private Float Debit;

    @Column(name = "Credit")
    private Float Credit;

    @Column(name = "ICurrencyID")
    private Integer ICurrencyID;

    @Column(name = "FExchangeRate")
    private Float FExchangeRate;

    @Column(name = "FForeignDebit")
    private Float FForeignDebit;

    @Column(name = "FForeignCredit")
    private Float FForeignCredit;

    @Column(name = "Description", length = 100)
    private String Description;

    @Column(name = "TaxTypeID")
    private Integer TaxTypeID;

    @JsonProperty("Reference")
    @Column(name = "Reference", length = 50)
    private String Reference;

    @Column(name = "Order_No", length = 50)
    private String Order_No;

    @Column(name = "ExtOrderNum", length = 50)
    private String ExtOrderNum;

    @Column(name = "CAuditNumber", length = 50)
    private String CAuditNumber;

    @Column(name = "Tax_Amount")
    private Float Tax_Amount;

    @Column(name = "FForeignTax")
    private Float FForeignTax;

    @Column(name = "Project")
    private Integer Project;

    @Column(name = "Period")
    private Integer Period;

    @Column(name = "DrCrAccount")
    private Integer DrCrAccount;

    @Column(name = "JobCodeLink")
    private Integer JobCodeLink;

    @Column(name = "CRCCheck")
    private Float CRCCheck;

    @Column(name = "DTStamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date DTStamp;

    @Column(name = "UserName", length = 50)
    private String UserName;

    @Column(name = "ITaxPeriodID")
    private Integer ITaxPeriodID;

    @Column(name = "CPayeeName", length = 100)
    private String CPayeeName;

    @Column(name = "BPrintCheque")
    private Boolean BPrintCheque;

    @Column(name = "CReference2", length = 50)
    private String CReference2;

    @Column(name = "RepID")
    private Integer RepID;

    @Column(name = "FJCRepCost")
    private Float FJCRepCost;

    @Column(name = "IMFPID")
    private Integer IMFPID;

    @Column(name = "BIsJCDocLine")
    private Boolean BIsJCDocLine;

    @Column(name = "BIsSTGLDocLine")
    private Boolean BIsSTGLDocLine;

    @Column(name = "IInvLineID")
    private Long IInvLineID;

    @Column(name = "ITxBranchID")
    private Integer ITxBranchID;

    @Column(name = "CBankRef", length = 20)
    private String CBankRef;

    @Column(name = "BPBTPaid")
    private Boolean BPBTPaid;

    @Column(name = "IGLTaxAccountID")
    private Integer IGLTaxAccountID;

    @Column(name = "BReconciled")
    private Boolean BReconciled;

    @Column(name = "XAttribute", columnDefinition = "XML")
    private String XAttribute;

    @Column(name = "PostGL_iBranchID")
    private Integer PostGL_iBranchID;

    @Column(name = "PostGL_dCreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date PostGL_dCreatedDate;

    @Column(name = "PostGL_dModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date PostGL_dModifiedDate;

    @Column(name = "PostGL_iCreatedBranchID")
    private Integer PostGL_iCreatedBranchID;

    @Column(name = "PostGL_iModifiedBranchID")
    private Integer PostGL_iModifiedBranchID;

    @Column(name = "PostGL_iCreatedAgentID")
    private Integer PostGL_iCreatedAgentID;

    @Column(name = "PostGL_iModifiedAgentID")
    private Integer PostGL_iModifiedAgentID;

    @Column(name = "PostGL_iChangeSetID")
    private Integer PostGL_iChangeSetID;

    @Column(name = "PostGL_Checksum", length = 20)
    private byte[] PostGL_Checksum;

    @Column(name = "iMajorIndustryCodeID")
    private Integer iMajorIndustryCodeID;

    @Column(name = "iImportDeclarationID")
    private Integer iImportDeclarationID;

    @Column(name = "CHash", length = 200)
    private String cHash;

    @Column(name = "iKeyVersion")
    private Integer iKeyVersion;



    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String toAccount;
//    private String transactionType;
//    private double exchangeRate;
//    private String description;
//    private String fromAccount;
//    private String reference;
//    private String currency;
//    private Double amount;
//    private LocalDate transactionDate;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PostGl postGl = (PostGl) o;
        return getAutoIdx() != null && Objects.equals(getAutoIdx(), postGl.getAutoIdx());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
