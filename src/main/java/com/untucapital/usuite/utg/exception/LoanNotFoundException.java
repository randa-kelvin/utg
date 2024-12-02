package com.untucapital.usuite.utg.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
//A custom exception handler for loan not found
public class LoanNotFoundException extends RuntimeException {
    private Integer id;
}
