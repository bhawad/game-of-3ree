package com.go3.application.model.game.gameplayers.exception;

import com.go3.infrastructure.exception.GameException;

public class GameSizeReachedException extends GameException {
    public GameSizeReachedException(String message) {
        super(message);
    }
}
