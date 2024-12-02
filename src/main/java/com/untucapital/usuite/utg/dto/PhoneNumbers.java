package com.untucapital.usuite.utg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PhoneNumbers {
    private String messageText;
    private List<String> number;
}
