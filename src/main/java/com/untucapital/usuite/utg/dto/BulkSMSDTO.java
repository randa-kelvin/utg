package com.untucapital.usuite.utg.dto;

import lombok.Data;

import java.util.List;

@Data
public class BulkSMSDTO {
    private List<String> phoneNumbers;
    private String message;
}
