package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionType {

    private int id;
    private String code;
    private String value;
    private boolean disbursement;
    private boolean repayment;
    private boolean accrual;

}
