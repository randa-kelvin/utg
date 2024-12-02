package com.untucapital.usuite.utg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class TokenExpiryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenExpiryException(String message) {
        super(message);
    }

    public TokenExpiryException(String message, Throwable cause) {
        super(message, cause);
    }
}
