package com.untucapital.usuite.utg.dto.client;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ViewClientLoansResponse {

    private LocalDate disbursementDate;

    private int loanId;
}
