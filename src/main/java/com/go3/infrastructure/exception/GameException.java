package com.go3.infrastructure.exception;

public abstract class GameException extends RuntimeException {

    public GameException(String message) {
        super(message);
    }

}
