package com.untucapital.usuite.utg.exception;

import com.untucapital.usuite.utg.controller.payload.UsuiteApiErrorResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<UsuiteApiErrorResp> handleValidationException(ValidationException ve) {
        log.error("Validation Exception occurred - {}", ve.getMessage(), ve);
        return ResponseEntity.badRequest()
                .body(new UsuiteApiErrorResp(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ve.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UsuiteApiErrorResp> handleValidationExceptions(MethodArgumentNotValidException mave) {
        log.error("Request Object Validation Exception occurred - {}", mave.getMessage(), mave);

        Map<String, String> errors = new HashMap<>();
        mave.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        UsuiteApiErrorResp apiErrorResp = new UsuiteApiErrorResp(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                mave.getMessage() + errors);
        return ResponseEntity.badRequest().body(apiErrorResp);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<UsuiteApiErrorResp> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        UsuiteApiErrorResp apiErrorResp = new UsuiteApiErrorResp(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), "Max upload file exceeded! File should not be larger than 20MB");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiErrorResp);
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<UsuiteApiErrorResp> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
        log.error("ResourceNotFound Exception occurred - {}", rnfe.getMessage(), rnfe);
        UsuiteApiErrorResp apiErrorResp = new UsuiteApiErrorResp(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResp);
    }

    @ResponseBody
    @ExceptionHandler(value = EmailException.class)
    public ResponseEntity<UsuiteApiErrorResp> handleEmailException(EmailException ee) {
        log.error("Email Exception occurred - {}", ee.getMessage(), ee);
        UsuiteApiErrorResp apiErrorResp = new UsuiteApiErrorResp(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ee.getMessage());
        return ResponseEntity.badRequest().body(apiErrorResp);
    }

    @ResponseBody
    @ExceptionHandler(value = HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<UsuiteApiErrorResp> handleUnauthorizedException(HttpClientErrorException.Unauthorized ue) {
        log.error("Unauthorized Exception occurred - {}", ue.getMessage(), ue);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new UsuiteApiErrorResp(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), ue.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(value = SecurityException.class)
    public ResponseEntity<UsuiteApiErrorResp> handleSecurityException(SecurityException ee) {
        log.error("Security Exception occurred - {}", ee.getMessage(), ee);
        UsuiteApiErrorResp apiErrorResp = new UsuiteApiErrorResp(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), ee.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiErrorResp);
    }

    @ExceptionHandler(value = ResourceNotFoundRequestException.class)
    public ResponseEntity<Object> handleObjectNotFound(ResourceNotFoundRequestException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ResourceNotFoundApiException apiException = new ResourceNotFoundApiException(e.getMessage(), httpStatus, LocalDateTime.now());

        return new ResponseEntity<>(apiException, httpStatus);
    }
}
