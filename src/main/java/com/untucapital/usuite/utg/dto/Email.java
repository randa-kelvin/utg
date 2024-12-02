package com.untucapital.usuite.utg.dto;

import lombok.Data;

@Data
public class Email {
    private String recipient;
    private String subject;
    private String message;

}
