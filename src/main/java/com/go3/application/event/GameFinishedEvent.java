package com.go3.application.event;

import com.go3.infrastructure.event.AbstractEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class GameFinishedEvent extends AbstractEvent {


    private final String lastPlayerId;
    private final String lastPlayerDisplayName;
    private final Integer currentNumber;
    private final Integer lastDecision;
    private final boolean gameWon;

    public GameFinishedEvent(String lastPlayerId, String lastPlayerDisplayName,
                             Integer currentNumber, Integer lastDecision, boolean gameWon) {
        super("GAME_FINISHED");
        this.lastPlayerId = lastPlayerId;
        this.lastPlayerDisplayName = lastPlayerDisplayName;
        this.currentNumber = currentNumber;
        this.lastDecision = lastDecision;
        this.gameWon = gameWon;
    }
}
