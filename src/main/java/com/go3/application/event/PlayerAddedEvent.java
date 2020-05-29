package com.go3.application.event;

import com.go3.infrastructure.event.AbstractEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PlayerAddedEvent extends AbstractEvent {

    private final String playerDisplayName;
    private final String playerId;
    private final String gameId;
    private final Integer startNumber;

    public PlayerAddedEvent(String playerDisplayName, String playerId, String gameId, Integer startNumber) {
        super("PLAYER_ADDED");
        this.playerDisplayName = playerDisplayName;
        this.playerId = playerId;
        this.gameId = gameId;
        this.startNumber = startNumber;
    }
}
