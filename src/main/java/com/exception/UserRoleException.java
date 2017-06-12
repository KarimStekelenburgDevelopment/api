package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class UserRoleException extends Exception {

    public UserRoleException()
    {}

    public UserRoleException(String message)
    {
        super(message);
    }

    public UserRoleException(Throwable cause)
    {
        super(cause);
    }


    public UserRoleException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
