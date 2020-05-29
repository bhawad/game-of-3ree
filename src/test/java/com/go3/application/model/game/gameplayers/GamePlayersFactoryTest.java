package com.go3.application.model.game.gameplayers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GamePlayersFactoryTest {

    @Test
    public void testCreateGamePlayers() {
        GamePlayersFactory factory = new GamePlayersFactory(10);
        IGamePlayers players = factory.create();

        Assert.assertNotNull("Players VO must not be null", players);
        Assert.assertTrue("Players VO must not be of correct type", players instanceof GamePlayers);
    }

}