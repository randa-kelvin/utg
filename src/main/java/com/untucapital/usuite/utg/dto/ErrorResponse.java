package com.untucapital.usuite.utg.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data

public class ErrorResponse {

    private String message;
    private String details;
    private LocalDateTime timestamp;
    private Integer code;

    public ErrorResponse(String message, String description, LocalDateTime now) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
        this.code = code;
    }
}
