package com.untucapital.usuite.utg.model.metabase;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MetabaseCurrency {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "decimal_places")
    private int decimalPlaces;

    @Column(name = "display_symbol")
    private String displaySymbol;

    // Getters and setters
}


