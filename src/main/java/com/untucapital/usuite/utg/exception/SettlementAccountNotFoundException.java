package com.untucapital.usuite.utg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SettlementAccountNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public SettlementAccountNotFoundException(String message) {
        super(message);
    }

    public SettlementAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
