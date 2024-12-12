package com.untucapital.usuite.utg.dto;

import com.untucapital.usuite.utg.model.ClientLoan;
import lombok.*;


@Setter
@Getter
public class LoanApplicationResponse {
    // Getter and Setter for message and clientLoan
    private String message;
    private ClientLoan clientLoan;

    // Constructor that takes both clientLoan and message
    public LoanApplicationResponse(ClientLoan clientLoan, String message) {
        this.clientLoan = clientLoan;
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoanApplicationResponse{" +
                "message='" + message + '\'' +
                ", clientLoan=" + clientLoan +
                '}';
    }
}


