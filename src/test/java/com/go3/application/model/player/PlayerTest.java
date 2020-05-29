package com.go3.application.model.player;

import com.go3.infrastructure.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.function.Supplier;

@RunWith(JUnit4.class)
public class PlayerTest {

    private final IPlayer testPlayer;

    public PlayerTest() {
        this.testPlayer = new Player("id", "name");
    }


    @Test(expected = ValidationException.class)
    public void testInvalidPlayerWithNullId() {
        new Player(null, "validName");
    }

    @Test(expected = ValidationException.class)
    public void testInvalidPlayerWithNullDisplayName() {
        new Player("validId", null);
    }


    @Test(expected = ValidationException.class)
    public void testPlayTurnWithInvalidInputNumber() {
        testPlayer.playTurn(-1, Mockito.mock(Supplier.class));
    }

    @Test(expected = ValidationException.class)
    public void testPlayTurnWithInvalidDecision() {
        testPlayer.playTurn(14, null);
    }

    @Test
    public void testPlayingValidTurn() {
        Supplier<TurnDecision> validDecision = Mockito.mock(Supplier.class);
        Mockito.when(validDecision.get()).thenReturn(TurnDecision.PLUS_ONE);

        Integer out = testPlayer.playTurn(14, validDecision);

        Assert.assertEquals("Turn output is not correct", Integer.valueOf(5), out);
    }
}