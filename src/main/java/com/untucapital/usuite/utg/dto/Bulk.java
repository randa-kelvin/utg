package com.untucapital.usuite.utg.dto;

import com.untucapital.usuite.utg.dto.BulkSMS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class Bulk {
    private String batchNumber;
    private List<BulkSMS> messages;
}
