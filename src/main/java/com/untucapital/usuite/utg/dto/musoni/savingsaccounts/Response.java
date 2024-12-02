package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {

    private String responseMsg;
    private Integer responseCode;
    private String Code;
}
