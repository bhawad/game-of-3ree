package com.go3.application.model.player.decisionSupplier;

import com.go3.application.model.player.TurnDecision;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public final class DecisionSupplierFactory {

    /**
     * Creates a manualDecision supplier based on the input parameters
     *
     * @param inputNumber    {@code Integer} must exist in case of automatic decision
     * @param manualDecision {@code TurnDecision} the player's manual decision. Can be null in case of automatic decision.
     * @param automatic      {@code boolean} flag indicates that the automatic decision should be favoured over the manual decision
     * @return {@code Supplier} the decision supplier based on the arguments
     * @throws {@code com.go3.infrastructure.exception.ValidationException} in case of null manual decision and false automatic flag
     *                {@code java.lang.IllegalArgumentException} in case of null input number and true automatic flag
     */
    public final Supplier<TurnDecision> create(Integer inputNumber, TurnDecision manualDecision, boolean automatic) {
        if (automatic) {
            return new AutomaticDecisionSupplier(inputNumber);
        } else {
            return new ManualDecisionSupplier(manualDecision);
        }
    }

}
