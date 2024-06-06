package com.untucapital.usuite.utg.dto.client.loan;

import lombok.Data;

@Data
public class Currency {
    private String code;
    private String name;
    private int decimalPlaces;
    private int inMultiplesOf;
    private String displaySymbol;
    private String nameCode;
    private String displayLabel;

    // Getters and Setters
    // ...
}
