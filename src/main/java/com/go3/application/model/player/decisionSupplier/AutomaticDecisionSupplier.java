package com.go3.application.model.player.decisionSupplier;

import com.go3.application.model.player.TurnDecision;
import com.go3.infrastructure.exception.ValidationException;

import java.util.function.Supplier;

/**
 * Supplier for a player automatic decision
 * Represents an AI supplier that generates a decision on behalf of the player
 * when the play should be done automatically
 * it detects the best decision for the input number
 */
final class AutomaticDecisionSupplier implements Supplier<TurnDecision> {

    private final Integer inputNumber;

    AutomaticDecisionSupplier(Integer inputNumber) {
        if (inputNumber == null) {
            throw new ValidationException("Input number must not be null");
        }
        this.inputNumber = inputNumber;
    }

    @Override
    public TurnDecision get() {

        Integer remainder = inputNumber % 3;

        switch (remainder) {
            case 0:
                return TurnDecision.ZERO;
            case 1:
                return TurnDecision.MINUS_ONE;
            default:
                return TurnDecision.PLUS_ONE;
        }
    }
}
