package com.go3.application.model.game.gameplayers;

import com.go3.application.model.player.IPlayer;

import java.util.Collection;

public interface IGamePlayers {

    /**
     * Get the player that should play the next turn
     *
     * @return {@code IPlayer} the player that must play the next turn
     * @throws RuntimeException if no players are added
     */
    IPlayer getNextPlayer();

    /**
     * Adds a player to the existing players
     *
     * @param player {@code IPlayer} player to be added
     * @throws RuntimeException in case the maximum size reached or the player can't be added
     */
    void addGamePlayer(IPlayer player);


    /**
     * Switch player turn index to the next player in turn
     */
    void switchToNextPlayer();

    /**
     * All player Ids inside the value object
     *
     * @return {@code Collection<String>}
     */
    Collection<String> getPlayerIds();


    /**
     * Indicates that all players have joined and they are ready to start
     *
     * @return {@code boolean} true if the players have joined and ready to start
     */
    boolean arePlayersReady();

}
