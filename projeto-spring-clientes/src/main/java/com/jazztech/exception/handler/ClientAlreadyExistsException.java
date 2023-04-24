package com.jazztech.exception.handler;

public class ClientAlreadyExistsException extends  Exception {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
