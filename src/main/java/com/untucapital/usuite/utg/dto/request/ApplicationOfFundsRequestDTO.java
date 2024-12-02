package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApplicationOfFundsRequestDTO extends AbstractEntityDTO {

    private String loanId;

    private String details;

    private String totalInvestments;
}
