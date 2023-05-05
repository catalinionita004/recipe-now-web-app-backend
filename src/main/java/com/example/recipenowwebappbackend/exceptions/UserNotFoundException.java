package com.example.recipenowwebappbackend.exceptions;

import javax.naming.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {
    public UserNotFoundException(final String msg) {
        super(msg);
    }
}
