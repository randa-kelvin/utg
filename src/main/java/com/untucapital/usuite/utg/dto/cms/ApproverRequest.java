package com.untucapital.usuite.utg.dto.cms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Getter
@Setter
@ToString
public class ApproverRequest {

    private Integer id;
    private String approvalStatus;
    private  String comment;

}
