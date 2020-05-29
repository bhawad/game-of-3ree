package com.go3.application.model.game;

import com.go3.application.model.game.gameplayers.GamePlayersFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GameFactoryTest {

    @Test
    public void testCreateGame() {
        GamePlayersFactory playersFactory = new GamePlayersFactory(2);
        GameFactory factory = new GameFactory(playersFactory);
        IGame game = factory.create(2);

        Assert.assertNotNull("Game must not be null", game);
        Assert.assertNotNull("Game Id must not be null", game.getId());
        Assert.assertTrue("Game must be of correct type", game instanceof Game);
        Assert.assertNotNull("Game players must not be null", game.getPlayers());
        Assert.assertEquals("Game first number must must be correct", Integer.valueOf(2), game.getLastNumber());
    }

}