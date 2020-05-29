package com.go3.application.model.player;

import com.go3.infrastructure.exception.ValidationException;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Supplier;

@EqualsAndHashCode(of = "id")
final class Player implements IPlayer {

    private final String id;
    private final String displayName;

    Player(String id, String displayName) {

        if (StringUtils.isBlank(id)) {
            throw new ValidationException("Player ID can't be empty");
        }

        if (StringUtils.isBlank(displayName)) {
            throw new ValidationException("Player Display-name can't be empty");
        }

        this.id = id;
        this.displayName = displayName;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public Integer playTurn(Integer inputNumber, Supplier<TurnDecision> decisionSupplier) throws ValidationException {

        if (decisionSupplier == null) {
            throw new ValidationException("A decision must be passed to play.");
        }

        if (inputNumber == null || inputNumber <= 1) {
            throw new ValidationException("Input number must be greater than or equal to one.");
        }

        return (inputNumber + decisionSupplier.get().getValue()) / 3;
    }
}
