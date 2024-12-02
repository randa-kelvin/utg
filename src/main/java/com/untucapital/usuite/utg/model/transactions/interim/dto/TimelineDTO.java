package com.untucapital.usuite.utg.model.transactions.interim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineDTO {
    private LocalDate actualDisbursementDate;
}

