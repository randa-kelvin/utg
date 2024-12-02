package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

@Data
public class Currency {

    private String code;
    private String name;
    private int decimalPlaces;
    private String displaySymbol;
    private String nameCode;
    private String displayLabel;

}
