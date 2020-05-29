package com.go3.application.event;

import com.go3.infrastructure.event.AbstractEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class GameCreatedEvent extends AbstractEvent {

    private final String gameId;
    private final Integer startNumber;

    public GameCreatedEvent(String gameId, Integer startNumber) {
        super("GAME_CREATED");
        this.gameId = gameId;
        this.startNumber = startNumber;
    }
}
