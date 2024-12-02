package com.untucapital.usuite.utg.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
/**
 * @author tjchidanika
 * @created 23/10/2023
 */

@RequiredArgsConstructor
@Getter
public class ResourceNotFoundApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;
}
