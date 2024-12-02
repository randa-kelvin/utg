package com.untucapital.usuite.utg.dto.response;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountsReceivableResponseDTO extends AbstractEntityDTO {

    private String loanId;
    private String customerName;
    private String dueSince;
    private String expectedPayment;
    private String paymentProbability;
    private String debtValue;
}
