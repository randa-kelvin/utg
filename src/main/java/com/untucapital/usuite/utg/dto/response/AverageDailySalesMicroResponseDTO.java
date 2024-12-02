package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AverageDailySalesMicroResponseDTO extends AbstractEntityDTO {

    private String loanId;
    private double monday;
    private double tuesday;
    private double wednesday;
    private double thursday;
    private double friday;
    private double saturday;
    private double sunday;

}
