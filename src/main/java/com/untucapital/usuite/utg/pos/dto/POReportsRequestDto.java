package com.untucapital.usuite.utg.pos.dto;

import lombok.Getter;
import lombok.Setter;

/**************************************
 ** @project utg
 ** @author Takunda Jimmy Chidanika    
 ** @created 2023/11/22                   
 **************************************
 */

@Getter
@Setter
public class POReportsRequestDto {
    private String status;
    private String user;
    private String fromDate;
    private String toDate;
}
