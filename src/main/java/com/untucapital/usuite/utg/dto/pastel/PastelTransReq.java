package com.untucapital.usuite.utg.dto.pastel;

import lombok.Data;

@Data
public class PastelTransReq {

    private String ToAccount;
    private String TransactionType;
    private Integer ExchangeRate;
    private  String Description;
    private String FromAccount;
    private String Reference;
    private String Currency;
    private double Amount;
    private String APIPassword;
    private String APIUsername;
    private String TransactionDate;
}
