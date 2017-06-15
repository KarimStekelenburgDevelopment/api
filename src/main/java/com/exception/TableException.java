package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class TableException extends Exception {

    public TableException()
    {}

    public TableException(String message)
    {
        super(message);
    }

    public TableException(Throwable cause)
    {
        super(cause);
    }


    public TableException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
