package com.go3.application.model.game;

import com.go3.application.model.game.gameplayers.IGamePlayers;
import com.go3.application.model.player.IPlayer;
import com.go3.application.model.player.TurnDecision;

import java.util.Optional;
import java.util.function.Supplier;

public interface IGame {

    /**
     * Game unique id
     *
     * @return {@code String} unique id
     */
    String getId();

    /**
     * Game players value object
     *
     * @return {@code IGamePlayers}
     */
    IGamePlayers getPlayers();

    /**
     * Last number reached in the game
     *
     * @return {@code Integer}
     */
    Integer getLastNumber();

    /**
     * Returns true if the game is won
     * The winner will always be the last player in this case
     *
     * @return {@code boolean}
     */
    boolean isWon();

    /**
     * Returns true if the game is finished i.e. someone has won or the game was finished without a winner
     *
     * @return {@code boolean}
     */
    boolean isGameFinished();

    /**
     * Play game turn
     *
     * @param playerId         {@code String} playerId of the turn , will be validated against the turn order
     * @param decisionSupplier {@code Supplier<TurnDecision>} supplier for the decision to play
     * @return {@code TurnOutput} A value object represents the result of the game play
     * @throws {@code java.lang.IllegalArgumentException} if the player id or the decision supplier are empty.
     *                {@code com.go3.application.model.game.exception.GamePlayException} if the given player is not the correct one in turn,
     *                or if the game is already won
     */
    TurnOutput doTurn(String playerId, Supplier<TurnDecision> decisionSupplier);

}
