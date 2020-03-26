package com.rabobank.userapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class UserNotFoundException extends Exception {

    public UserNotFoundException() { }
    public UserNotFoundException(String exception) { super(exception); }
}
