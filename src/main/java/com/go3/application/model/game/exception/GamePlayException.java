package com.go3.application.model.game.exception;

import com.go3.infrastructure.exception.GameException;

/**
 * Exception class that may occur during the game play
 */
public class GamePlayException extends GameException {
    public GamePlayException(String message) {
        super(message);
    }
}
