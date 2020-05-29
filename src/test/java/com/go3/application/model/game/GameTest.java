package com.go3.application.model.game;

import com.go3.application.model.game.exception.GamePlayException;
import com.go3.application.model.game.gameplayers.IGamePlayers;
import com.go3.application.model.player.IPlayer;
import com.go3.application.model.player.TurnDecision;
import com.go3.infrastructure.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.function.Supplier;

@RunWith(JUnit4.class)
public class GameTest {


    @Test(expected = ValidationException.class)
    public void testGameWithoutId() {
        new Game(null, Mockito.mock(IGamePlayers.class), 20);
    }

    @Test(expected = ValidationException.class)
    public void testGameWithoutPlayersVO() {
        new Game("id", null, 20);
    }

    @Test(expected = ValidationException.class)
    public void testGameWithInvalidInitialNumber() {
        new Game("id", Mockito.mock(IGamePlayers.class), 0);
    }

    @Test
    public void testDoTurnInvalidPlayer() {
        IGamePlayers mockPlayers = Mockito.mock(IGamePlayers.class);
        IPlayer testNextPlayer = Mockito.mock(IPlayer.class);
        IGame testGame = new Game("id", mockPlayers, 10);

        Mockito.when(mockPlayers.getNextPlayer()).thenReturn(testNextPlayer);
        Mockito.when(testNextPlayer.getId()).thenReturn("right_id");

        GamePlayException exception = Assert.assertThrows("Player must not be able to play in the wrong turn",
                GamePlayException.class,
                () -> testGame.doTurn("wrong_id", Mockito.mock(Supplier.class)));

        Assert.assertEquals("This is not the correct turn", exception.getMessage());
    }

    @Test
    public void testDoTurnFinishedGameWithNoWinners() throws NoSuchFieldException {

        //setup
        IGamePlayers mockPlayers = Mockito.mock(IGamePlayers.class);
        IGame testGame = new Game("id", mockPlayers, 3);
        Supplier decisionSupplier = Mockito.mock(Supplier.class);
        Field lastNumber = Game.class.getDeclaredField("lastNumber");
        lastNumber.setAccessible(true);
        ReflectionUtils.setField(lastNumber, testGame, 0);

        //execute
        GamePlayException exception = Assert.assertThrows("Finished game can't be played",
                GamePlayException.class,
                () -> testGame.doTurn("playerId", decisionSupplier));

        //assert
        Assert.assertEquals("Game was already finished with no winner", exception.getMessage());
    }

    @Test
    public void testDoTurnFinishedGameWithWinner() throws NoSuchFieldException {

        //setup
        IGamePlayers mockPlayers = Mockito.mock(IGamePlayers.class);
        IGame testGame = new Game("id", mockPlayers, 3);
        Supplier decisionSupplier = Mockito.mock(Supplier.class);
        Field lastNumber = Game.class.getDeclaredField("lastNumber");
        lastNumber.setAccessible(true);
        ReflectionUtils.setField(lastNumber, testGame, 1);

        //execute
        GamePlayException exception = Assert.assertThrows("Finished game can't be played",
                GamePlayException.class,
                () -> testGame.doTurn("playerId", decisionSupplier));

        //assert
        Assert.assertEquals("Game was already Won", exception.getMessage());
    }


    @Test
    public void testDoNormalTurn() {
        //setup
        IGamePlayers mockPlayers = Mockito.mock(IGamePlayers.class);
        IGame testGame = new Game("id", mockPlayers, 10);
        IPlayer testCurrentPlayer = Mockito.mock(IPlayer.class);
        Mockito.when(testCurrentPlayer.getId()).thenReturn("playerId");
        Mockito.when(mockPlayers.getNextPlayer()).thenReturn(testCurrentPlayer);
        Supplier decisionSupplier = Mockito.mock(Supplier.class);
        Mockito.when(decisionSupplier.get()).thenReturn(TurnDecision.MINUS_ONE);
        Mockito.when(testCurrentPlayer.playTurn(Mockito.any(), Mockito.any(Supplier.class))).thenReturn(9);

        //execute
        TurnOutput output = testGame.doTurn("playerId", decisionSupplier);


        //assert
        Assert.assertEquals("Turn output must be identical", TurnOutput.builder()
                        .turnDecision(TurnDecision.MINUS_ONE)
                        .turnResult(9)
                        .turnPlayer(testCurrentPlayer)
                        .nextPlayer(testCurrentPlayer)
                        .build(),
                output);
    }

    @Test
    public void testDoFinalWinningTurn() {

        //setup
        IGamePlayers mockPlayers = Mockito.mock(IGamePlayers.class);
        IGame testGame = new Game("id", mockPlayers, 3);
        IPlayer testCurrentPlayer = Mockito.mock(IPlayer.class);
        Mockito.when(testCurrentPlayer.getId()).thenReturn("playerId");
        Mockito.when(mockPlayers.getNextPlayer()).thenReturn(testCurrentPlayer);
        Supplier decisionSupplier = Mockito.mock(Supplier.class);
        Mockito.when(decisionSupplier.get()).thenReturn(TurnDecision.ZERO);
        Mockito.when(testCurrentPlayer.playTurn(Mockito.any(), Mockito.any(Supplier.class))).thenReturn(1);

        //execute
        TurnOutput output = testGame.doTurn("playerId", decisionSupplier);


        //assert
        Assert.assertEquals("Turn output must be identical", TurnOutput.builder()
                        .turnDecision(TurnDecision.ZERO)
                        .turnResult(1)
                        .turnPlayer(testCurrentPlayer)
                        .nextPlayer(null)
                        .build(),
                output);

        Assert.assertTrue("Game must be finished", testGame.isGameFinished());
        Assert.assertTrue("Game must be won", testGame.isWon());

    }


    @Test
    public void testGettingGameLastNumber() {
        //setup
        IGamePlayers mockPlayers = Mockito.mock(IGamePlayers.class);
        IGame testGame = new Game("id", mockPlayers, 9);
        IPlayer testCurrentPlayer = Mockito.mock(IPlayer.class);
        Mockito.when(testCurrentPlayer.getId()).thenReturn("playerId");
        Mockito.when(mockPlayers.getNextPlayer()).thenReturn(testCurrentPlayer);
        Supplier decisionSupplier = Mockito.mock(Supplier.class);
        Mockito.when(decisionSupplier.get()).thenReturn(TurnDecision.ZERO);
        Mockito.when(testCurrentPlayer.playTurn(Mockito.any(), Mockito.any(Supplier.class))).thenReturn(3);

        //execute
        Integer firstNumber = testGame.getLastNumber();
        testGame.doTurn("playerId", decisionSupplier);
        Integer firstNumberAfterTurn = testGame.getLastNumber();

        //assert
        Assert.assertEquals("first number is not identical", Integer.valueOf(9), firstNumber);
        Assert.assertEquals("last number is not identical", Integer.valueOf(3), firstNumberAfterTurn);

    }
}