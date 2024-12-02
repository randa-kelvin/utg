package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MusoniClientResponseDTO extends AbstractEntityDTO {


    private String clientId;

    private String accountNo;

    private String externalId;

    private String status;

    private String activationDate;

    private String firstname;

    private String lastname;

    private String displayName;

    private String officeName;

    private String submittedByUsername;

    private String savingsAccountId;

}
