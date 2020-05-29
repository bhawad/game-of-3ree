package com.go3.application.model.game.gameplayers.exception;

import com.go3.infrastructure.exception.GameException;

public class PlayerAlreadyExistsException extends GameException {
    public PlayerAlreadyExistsException(String message) {
        super(message);
    }
}
