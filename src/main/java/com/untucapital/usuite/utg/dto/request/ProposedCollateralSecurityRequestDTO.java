package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class ProposedCollateralSecurityRequestDTO extends AbstractEntityDTO {


    private String loanId;

    private String type;

    private String description;

    private String ownership;

    private String marketValue;

    private String discountValue;


}
