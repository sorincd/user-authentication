package com.userauth.userauth.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserAuthException extends RuntimeException {

    private final String id;

    protected UserAuthException(String message) {
        super(message);
        this.id =  UUID.randomUUID().toString();
    }

    public static UserAuthException userAuthException(String message) {
        return new UserAuthException(message);
    }

    public static UserAuthException userAuthException(Throwable throwable) {
        return new UserAuthException(throwable.getMessage());
    }
}
