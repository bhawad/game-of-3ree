package com.go3.application.model.player;

import java.util.function.Supplier;

public interface IPlayer {

    /**
     * Player unique id
     *
     * @return {@code String} Player id
     */
    String getId();

    /**
     * Player display name
     *
     * @return {@code String} Player display name
     */
    String displayName();


    /**
     * Do play turn
     *
     * @param inputNumber      {@code Integer} the turn input number. Not null and GT 1
     * @param decisionSupplier {@code Supplier<TurnDecision>} decision supplier to play. Not null
     * @return {@code Integer} the output of playing i.e. (input + decision) /3
     * @throws {@code com.go3.infrastructure.exception.ValidationException} in case the input number is not valid
     *                {@Code java.lang.IllegalArgumentException} in case the decision supplier is null
     */
    Integer playTurn(Integer inputNumber, Supplier<TurnDecision> decisionSupplier);
}
