package com.go3.application.model.player;

import org.springframework.stereotype.Component;

@Component
public final class PlayerFactory {


    /**
     * Create a new player
     *
     * @param id          {@code String} player id. Not Empty.
     * @param displayName {@code String} player displayname. Not Empty.
     * @return {@code IPlayer}
     * @throws {@code java.lang.IllegalArgumentException} case the id is empty
     *                {@code com.go3.infrastructure.exception.ValidationException} case the display name is empty
     */
    public final IPlayer create(String id, String displayName) {
        return new Player(id, displayName);
    }

}
