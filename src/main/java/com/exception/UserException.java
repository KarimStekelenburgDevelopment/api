package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class UserException extends Exception {

    public UserException()
    {}

    public UserException(String message)
    {
        super(message);
    }

    public UserException(Throwable cause)
    {
        super(cause);
    }


    public UserException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
