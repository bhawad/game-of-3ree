package com.go3.application.event;

import com.go3.infrastructure.event.AbstractEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class NextTurnEvent extends AbstractEvent {

    private final String nextPlayerId;
    private final String nextPlayerName;
    private final Integer currentNumber;
    private final Integer lastDecision;
    private final String lastPlayerId;
    private final String lastPlayerDisplayName;

    public NextTurnEvent(String nextPlayerId, String nextPlayerName,
                         Integer currentNumber, Integer lastDecision,
                         String lastPlayerId, String lastPlayerDisplayName) {
        super("NEXT_TURN");
        this.nextPlayerId = nextPlayerId;
        this.nextPlayerName = nextPlayerName;
        this.currentNumber = currentNumber;
        this.lastDecision = lastDecision;
        this.lastPlayerId = lastPlayerId;
        this.lastPlayerDisplayName = lastPlayerDisplayName;
    }
}
