package com.controller;

import com.exception.UserException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error", ex.getMessage());



        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

class ErrorMessage {
    private String code;
    private String message;
    private String details;

    public ErrorMessage(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

