package com.go3.application.model.game;

import com.go3.application.model.game.gameplayers.GamePlayersFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public final class GameFactory {

    private final GamePlayersFactory gamePlayersFactory;

    /**
     * Create a new game with the given initial number
     *
     * @param initialNumber {@code Integer}
     * @return {@code IGame} the created Game
     */
    public final IGame create(Integer initialNumber) {
        return new Game(UUID.randomUUID().toString(), gamePlayersFactory.create(), initialNumber);
    }

}
