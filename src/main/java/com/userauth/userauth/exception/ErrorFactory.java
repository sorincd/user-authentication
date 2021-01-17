package com.userauth.userauth.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorFactory {

    public static Error error(UserAuthException exception, List<String> messages) {
        return new Error(exception.getId(), messages);
    }

    public static Error error(UserAuthException exception) {
        return new Error(exception.getId(), List.of(exception.getMessage()));
    }
}
