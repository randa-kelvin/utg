package com.untucapital.usuite.utg.model.transactions.interim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.untucapital.usuite.utg.model.transactions.TransactionType}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeDTO implements Serializable {
    private int id;
    private String code;
    private String value;
    private boolean deposit;
    private Boolean disbursement;
    private Boolean repaymentAtDisbursement;
    private Boolean repayment;



}
