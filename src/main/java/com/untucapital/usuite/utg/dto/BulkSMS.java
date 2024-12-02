package com.untucapital.usuite.utg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class BulkSMS {
    private String originator;
    private String destination;
    private String messageText;
    private String messageReference;
}
