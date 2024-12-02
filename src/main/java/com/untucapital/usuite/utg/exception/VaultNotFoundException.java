package com.untucapital.usuite.utg.exception;

import lombok.Data;

@Data
public class VaultNotFoundException extends RuntimeException {

    public VaultNotFoundException(String message) {
        super(message);
    }

    public VaultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Add additional constructors for flexibility

    public VaultNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


