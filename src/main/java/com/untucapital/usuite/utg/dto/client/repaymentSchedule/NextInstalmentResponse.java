package com.untucapital.usuite.utg.dto.client.repaymentSchedule;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NextInstalmentResponse {

    private String loanId;
    private Double amountDue;
    private LocalDate dueDate;

}
