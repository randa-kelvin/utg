package com.untucapital.usuite.utg.model.transactions.interim.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.untucapital.usuite.utg.dto.musoni.savingsaccounts.Currency}
 */
@Data
public class CurrencyDTO implements Serializable {
    private String code;
    private String name;
    private Integer decimalPlaces;
    private Integer inMultiplesOf;
    private String displaySymbol;
    private String nameCode;
    private String displayLabel;
}
