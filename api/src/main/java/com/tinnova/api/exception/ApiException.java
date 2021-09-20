package com.tinnova.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiException(String message) {
        super(message);
    }

}