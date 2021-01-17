package com.userauth.userauth.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class Error {
    private final String id;
    private final List<String> messages;

    public Error(String id, List<String> messages) {
        this.id = id;
        this.messages = messages;
    }
}
