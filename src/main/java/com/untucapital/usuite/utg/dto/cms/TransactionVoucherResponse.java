package com.untucapital.usuite.utg.dto.cms;

import com.untucapital.usuite.utg.dto.cms.res.Response;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author tjchidanika
 * @created 9/10/2023
 */

@Data
public class TransactionVoucherResponse extends Response {

    private Integer id;
    private String referenceNumber;

    private UserDTO initiator;
    private LocalDate applicationDate;
    private String applicationNo;

    private UserDTO firstApprover;
    private String firstApprovedAt;
    private ApprovalStatus firstApprovalStatus;
    private String  firstApprovalComment;

    private UserDTO secondApprover;
    private String secondApprovedAt;
    private ApprovalStatus secondApprovalStatus;
    private String  secondApprovalComment;

    private BigDecimal amount;
    private String amountInWords;
    private String withdrawalPurpose;
    private String currency;

    private Integer denomination100;
    private Integer denomination50;
    private Integer denomination20;
    private Integer denomination10;
    private Integer denomination5;
    private Integer denomination2;
    private Integer denomination1;
    private Integer denominationCents;

    private BranchDTO branch;
    private VaultDTO fromVault;
    private String reference;
    private VaultDTO toVault;



}
