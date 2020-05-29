package com.go3.application.model.game.gameplayers;

import com.go3.application.model.game.exception.GamePlayException;
import com.go3.application.model.game.gameplayers.exception.GameSizeReachedException;
import com.go3.application.model.game.gameplayers.exception.PlayerAlreadyExistsException;
import com.go3.application.model.player.IPlayer;
import com.go3.infrastructure.exception.ValidationException;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

class GamePlayers implements IGamePlayers {


    private final List<IPlayer> players;
    private final Integer gameSize;

    private Integer nextPlayerIndex;

    GamePlayers(Integer gameSize) {

        if (gameSize == null || gameSize < 2) {
            throw new ValidationException("Players size must be at least 2");
        }

        this.gameSize = gameSize;
        this.players = new CopyOnWriteArrayList<>();
        this.nextPlayerIndex = 0;
    }

    @Override
    public IPlayer getNextPlayer() {
        if (players.size() == 0) {
            throw new GamePlayException("No players are added");
        }
        return players.get(nextPlayerIndex);
    }

    @Override
    public void addGamePlayer(IPlayer player) {
        if (player == null) {
            throw new ValidationException("Player to add must not be empty");
        }

        if (players.size() >= gameSize) {
            throw new GameSizeReachedException("Maximum Game Size reached");
        }

        if (players.contains(player)) {
            throw new PlayerAlreadyExistsException("Player is already added to the game");
        }

        players.add(player);
    }

    @Override
    public void switchToNextPlayer() {
        if (players.size() == 0 || players.size() < gameSize) {
            return; // do nothing , next player stays the same
        }

        if (nextPlayerIndex < players.size() - 1) {
            nextPlayerIndex++;
        } else {
            nextPlayerIndex = 0;
        }
    }

    @Override
    public Collection<String> getPlayerIds() {
        return players.stream().map(IPlayer::getId).collect(Collectors.toList());
    }

    @Override
    public boolean arePlayersReady() {
        return players.size() >= gameSize;
    }
}
