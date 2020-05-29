package com.go3.application.model.game.gameplayers;

import com.go3.application.model.game.exception.GamePlayException;
import com.go3.application.model.game.gameplayers.exception.GameSizeReachedException;
import com.go3.application.model.game.gameplayers.exception.PlayerAlreadyExistsException;
import com.go3.application.model.player.IPlayer;
import com.go3.infrastructure.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

@RunWith(JUnit4.class)
public class GamePlayersTest {


    @Test(expected = ValidationException.class)
    public void testGamePlayersWithInvalidPlayerSize() {
        new GamePlayers(0);
    }

    @Test(expected = ValidationException.class)
    public void testAddingInvalidPlayer() {
        GamePlayers testPlayers = new GamePlayers(2);
        testPlayers.addGamePlayer(null);
    }

    @Test(expected = GameSizeReachedException.class)
    public void testAddingOverSizePlayers() {
        GamePlayers testPlayers = new GamePlayers(2);
        testPlayers.addGamePlayer(Mockito.mock(IPlayer.class));
        testPlayers.addGamePlayer(Mockito.mock(IPlayer.class));
        testPlayers.addGamePlayer(Mockito.mock(IPlayer.class));
    }

    @Test(expected = PlayerAlreadyExistsException.class)
    public void testAddingAlreadyExistingPlayer() {
        IPlayer testPlayer1 = Mockito.mock(IPlayer.class);

        GamePlayers testPlayers = new GamePlayers(2);
        testPlayers.addGamePlayer(testPlayer1);
        testPlayers.addGamePlayer(testPlayer1);
    }


    @Test(expected = GamePlayException.class)
    public void testNextPlayerWithNoPlayersAdded() {
        GamePlayers testPlayers = new GamePlayers(2);
        testPlayers.getNextPlayer();
    }

    @Test
    public void testNextPlayerWithPlayersAdded() {
        GamePlayers testPlayers = new GamePlayers(2);
        IPlayer player1 = Mockito.mock(IPlayer.class);
        testPlayers.addGamePlayer(player1);

        IPlayer result = testPlayers.getNextPlayer();

        Assert.assertEquals("Next Player must be identical", player1, result);
    }

    @Test
    public void testSwitchToNextPlayerWithNoPlayers() {
        GamePlayers testPlayers = new GamePlayers(2);
        testPlayers.switchToNextPlayer();

        //nothing should happen
    }

    @Test
    public void testSwitchToNextPlayerWithNotReadyPlayers() {
        GamePlayers testPlayers = new GamePlayers(3);

        IPlayer player1 = Mockito.mock(IPlayer.class);
        IPlayer player2 = Mockito.mock(IPlayer.class);
        testPlayers.addGamePlayer(player1);
        testPlayers.addGamePlayer(player2);

        testPlayers.switchToNextPlayer();

        //nothing should happen
        IPlayer result = testPlayers.getNextPlayer();

        Assert.assertEquals("Next Player must be identical", player1, result);
    }

    @Test
    public void testSuccessfullySwitchingToNextPlayer() {
        GamePlayers testPlayers = new GamePlayers(2);

        IPlayer player1 = Mockito.mock(IPlayer.class);
        IPlayer player2 = Mockito.mock(IPlayer.class);
        testPlayers.addGamePlayer(player1);
        testPlayers.addGamePlayer(player2);

        testPlayers.switchToNextPlayer();

        //assert player will be switched
        IPlayer result = testPlayers.getNextPlayer();

        Assert.assertEquals("Next Player must be identical", player2, result);
    }

    @Test
    public void testPlayersReady() {
        GamePlayers testPlayers = new GamePlayers(2);

        IPlayer player1 = Mockito.mock(IPlayer.class);
        IPlayer player2 = Mockito.mock(IPlayer.class);
        testPlayers.addGamePlayer(player1);
        testPlayers.addGamePlayer(player2);

        Assert.assertTrue("Players should be ready", testPlayers.arePlayersReady());
    }

    @Test
    public void testGetPlayerIds() {
        GamePlayers testPlayers = new GamePlayers(2);

        IPlayer player1 = Mockito.mock(IPlayer.class);
        IPlayer player2 = Mockito.mock(IPlayer.class);
        Mockito.when(player1.getId()).thenReturn("id1");
        Mockito.when(player2.getId()).thenReturn("id2");
        testPlayers.addGamePlayer(player1);
        testPlayers.addGamePlayer(player2);

        Collection<String> playerIds = testPlayers.getPlayerIds();

        Assert.assertTrue("Player ids must have all added idds",
                playerIds.containsAll(Arrays.asList("id1", "id2")));
    }
}