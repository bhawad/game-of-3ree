package com.go3.application.repository.exception;


import com.go3.infrastructure.exception.GameException;

public class GameNotFoundException extends GameException {
    public GameNotFoundException(String message) {
        super(message);
    }
}
