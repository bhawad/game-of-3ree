package com.go3.application.model.player.decisionSupplier;

import com.go3.application.model.player.TurnDecision;
import com.go3.infrastructure.exception.ValidationException;
import org.springframework.util.Assert;

import java.util.function.Supplier;

/**
 * Supplier for player manual decision
 * validates and returns the player's chosen decision
 */
final class ManualDecisionSupplier implements Supplier<TurnDecision> {


    private final TurnDecision manualDecision;

    ManualDecisionSupplier(TurnDecision manualDecision) {
        if(manualDecision == null){
            throw new ValidationException("Input decision must not be null");
        }

        this.manualDecision = manualDecision;
    }

    @Override
    public TurnDecision get() {
        return manualDecision;
    }
}
