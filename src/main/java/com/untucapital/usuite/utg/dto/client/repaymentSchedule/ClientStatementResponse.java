package com.untucapital.usuite.utg.dto.client.repaymentSchedule;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientStatementResponse {

    private Integer period;
    private LocalDate dueDate;
    private Double amountDue;
    private Double amountPaid;
    private Double amountOutstanding;
    private LocalDate paidBy;
}
