package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "proposedCollateralSecurity")
public class ProposedCollateralSecurity extends AbstractEntity {

    @Column(name = "loan_id", nullable = false)
    @NotNull
    private String loanId;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "ownership")
    private String ownership;

    @Column(name = "market_value")
    private String marketValue;

    @Column(name = "discount_value")
    private String discountValue;


    public ProposedCollateralSecurity() {

    }
}
