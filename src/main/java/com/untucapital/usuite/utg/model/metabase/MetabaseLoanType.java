package com.untucapital.usuite.utg.model.metabase;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MetabaseLoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;

    // Getters and setters
}


