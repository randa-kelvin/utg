package com.untucapital.usuite.utg.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tjchidanika
 * @created 16/6/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//A custom exception handler for loan list cannot be null
public class LoanListCannotBeNullExceptionHandler extends RuntimeException {
    private String message;
}
