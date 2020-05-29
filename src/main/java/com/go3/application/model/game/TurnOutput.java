package com.go3.application.model.game;

import com.go3.application.model.player.IPlayer;
import com.go3.application.model.player.TurnDecision;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Value object of the Turn output
 */
@RequiredArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public final class TurnOutput {

    private final TurnDecision turnDecision;
    private final Integer turnResult;
    private final IPlayer turnPlayer;
    private final IPlayer nextPlayer;

}
