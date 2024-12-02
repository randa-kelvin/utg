package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BranchesResponseDTO extends AbstractEntityDTO {

    private String branchName;
    private String branchAddress;
    private String branchTellPhone;
    private String branchStatus;
    private String vaultAccountNumber;
    private String branchCode;
    private String googleMap;
    private String directionsLink;
    private int code;

}
