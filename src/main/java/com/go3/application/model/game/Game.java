package com.go3.application.model.game;

import com.go3.application.model.game.exception.GamePlayException;
import com.go3.application.model.game.gameplayers.IGamePlayers;
import com.go3.application.model.player.IPlayer;
import com.go3.application.model.player.TurnDecision;
import com.go3.infrastructure.exception.ValidationException;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Supplier;

@EqualsAndHashCode(of = "id")
final class Game implements IGame {

    private final String id;
    private final IGamePlayers players;

    private Integer lastNumber;
    private boolean gameFinished;
    private boolean gameWon;

    public Game(String id, IGamePlayers players, Integer initialNumber) {
        if (StringUtils.isBlank(id)) {
            throw new ValidationException("Game Id can't be empty");
        }

        if (players == null) {
            throw new ValidationException("Players value object can't be empty");
        }

        if (initialNumber == null || initialNumber <= 1) {
            throw new ValidationException("Initial number must be not smaller than  or equal to 1");
        }

        this.id = id;
        this.players = players;
        this.lastNumber = initialNumber;
    }

    @Override
    public Integer getLastNumber() {
        return lastNumber;
    }

    @Override
    public TurnOutput doTurn(String playerId, Supplier<TurnDecision> decisionSupplier) {

        if (StringUtils.isBlank(playerId)) {
            throw new ValidationException("A turn must have a player");
        }

        //check if game is won already
        if (lastNumber == 1) {
            throw new GamePlayException("Game was already Won");
        }

        if (lastNumber < 1) {
            throw new GamePlayException("Game was already finished with no winner");
        }

        //find next player in turn and validate on the correct turn
        IPlayer turnPlayer = players.getNextPlayer();
        if (!Objects.equals(turnPlayer.getId(), playerId)) {
            throw new GamePlayException("This is not the correct turn");
        }

        //play and get output
        Integer playerResult = turnPlayer.playTurn(lastNumber, decisionSupplier);

        //update game state
        lastNumber = playerResult;
        gameWon = playerResult == 1;
        gameFinished = playerResult <= 1;

        //if game is not finished the switch turn
        IPlayer nextPlayer = null;
        if (!gameFinished) {
            players.switchToNextPlayer();
            nextPlayer = players.getNextPlayer();
        }

        return TurnOutput.builder()
                .turnDecision(decisionSupplier.get())
                .turnResult(playerResult)
                .turnPlayer(turnPlayer)
                .nextPlayer(nextPlayer)
                .build();
    }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }

    @Override
    public boolean isWon() {
        return gameWon;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public IGamePlayers getPlayers() {
        return players;
    }
}
