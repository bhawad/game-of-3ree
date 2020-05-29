package com.go3.application.model.game.gameplayers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class GamePlayersFactory {


    private final Integer size;

    public GamePlayersFactory(@Value("${go3.gameplayers.size}") Integer size) {
        this.size = size;
    }


    /**
     * Creates a game players value object based on the preconfigured players game size, under the property 'go3.gameplayers.size'
     *
     * @return {@code IGamePlayers}
     * @throws {@code com.go3.infrastructure.exception.ValidationException} case the configured size is less than 2
     */
    public final IGamePlayers create() {
        return new GamePlayers(size);
    }

}
