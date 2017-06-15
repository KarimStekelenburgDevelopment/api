package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class AreaException extends Exception {

    public AreaException()
    {}

    public AreaException(String message)
    {
        super(message);
    }

    public AreaException(Throwable cause)
    {
        super(cause);
    }


    public AreaException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
