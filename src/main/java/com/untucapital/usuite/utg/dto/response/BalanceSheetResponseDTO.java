package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BalanceSheetResponseDTO extends AbstractEntityDTO {

    private String id;
    private String loanId;
    private String name;
    private String comment;
    private double amount;

}
