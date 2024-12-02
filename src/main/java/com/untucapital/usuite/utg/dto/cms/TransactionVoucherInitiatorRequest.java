package com.untucapital.usuite.utg.dto.cms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */
@Getter
@Setter
@ToString
public class TransactionVoucherInitiatorRequest {

    private String initiator;
//    private LocalDateTime applicationDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;


    private BigDecimal amount;
    private String fromVault;
    private String vaultCode;
    private String toVault;

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

    private Integer totalDenominations;

    private String firstApprover;
    private String secondApprover;

    private boolean duplicate;
}
