package com.untucapital.usuite.utg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KycNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public KycNotFoundException(String message) {
        super(message);
    }

    public KycNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
