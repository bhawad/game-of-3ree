package com.go3.application.model.player;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PlayerFactoryTest {

    private final PlayerFactory testFactory;

    public PlayerFactoryTest() {
        this.testFactory = new PlayerFactory();
    }


    @Test
    public void testPlayerCreation() {
        String testId = "testId";
        String testName = "testName";

        IPlayer player = testFactory.create(testId, testName);

        Assert.assertEquals("Player id must be identical", testId, player.getId());
        Assert.assertEquals("Player name must be identical", testName, player.displayName());
    }
}