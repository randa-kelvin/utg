package com.untucapital.usuite.utg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SMSDto {
    private String originator;
    private String destination;
    private String messageText;
    private String messageReference;
    private String messageDate;
    private String messageValidity;
    private String sendDateTime;

}
