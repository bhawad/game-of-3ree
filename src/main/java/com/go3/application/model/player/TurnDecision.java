package com.go3.application.model.player;

/**
 * Enum of the three valid input user decisions for every turn
 */
public enum TurnDecision {

    MINUS_ONE(-1), ZERO(0), PLUS_ONE(1);

    private final Integer value;

    TurnDecision(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
