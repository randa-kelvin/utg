package com.untucapital.usuite.utg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.ServerException;

@Component
//A custom error handler for the rest template
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    //Check if the response has an error
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError();
    }

    @Override
    //Handle the error
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()){
            if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE){
                throw new ServerException("Service is currently unavailable");
            }
        }
        if (response.getStatusCode().is4xxClientError()){
            if (response.getStatusCode()==HttpStatus.NOT_FOUND){
                throw new FileNotFoundException("Resource not found");
            }
        }
        if (response.getStatusCode().is4xxClientError()){
            if (response.getStatusCode()==HttpStatus.REQUEST_TIMEOUT){
                throw new FileNotFoundException("Request Timed Out");
            }
        }
    }
}
