package com.untucapital.usuite.utg.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaturedInterestReportDTO {
    // Getters and Setters
    private LocalDate transactionDate;         // Date of the transaction
    private String transactionDescription;     // Description of the transaction (e.g., interest applied, repayment)
    private double debit;                      // Debit amount for the transaction
    private double credit;                     // Credit amount for the transaction
    private double balance;                    // Running balance after the transaction

}
