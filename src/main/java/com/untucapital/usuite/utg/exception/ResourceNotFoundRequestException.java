package com.untucapital.usuite.utg.exception;

import lombok.RequiredArgsConstructor;

/**
 * @author tjchidanika
 * @created 23/10/2023
 */


public class ResourceNotFoundRequestException extends RuntimeException{

    public ResourceNotFoundRequestException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Could not find %s with %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
