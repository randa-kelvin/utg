package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cms_transaction_voucher")
@Getter
@Setter
public class TransactionVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User initiator;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Vault fromVault;

    private String reference;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Vault toVault;

    @Column(name = "amount_in_words")
    private String amountInWords;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Integer withdrawalPurpose;

    @Column(name = "currency")
    private String currency;

    @Column(name = "denomination_100")
    private Integer denomination100;

    @Column(name = "denomination_50")
    private Integer denomination50;

    @Column(name = "denomination_20")
    private Integer denomination20;

    @Column(name = "denomination_10")
    private Integer denomination10;

    @Column(name = "denomination_5")
    private Integer denomination5;

    @Column(name = "denomination_2")
    private Integer denomination2;

    @Column(name = "denomination_1")
    private Integer denomination1;

    @Column(name = "denomination_cents")
    private Integer denominationCents;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User firstApprover;

    @Column(name = "first_approved_at")
    private LocalDateTime firstApprovedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "first_approval_status")
    private ApprovalStatus firstApprovalStatus;

    @Column(name = "first_approval_comment")
    private String firstApprovalComment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User secondApprover;

    @Column(name = "second_approved_at")
    private LocalDateTime secondApprovedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "second_approval_status")
    private ApprovalStatus secondApprovalStatus;

    @Column(name = "second_approval_comment")
    private String secondApprovalComment;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branches branch;
}
