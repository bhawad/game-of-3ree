package com.go3.application.repository;

import com.go3.Go3Application;
import com.go3.application.model.game.GameFactory;
import com.go3.application.model.game.IGame;
import com.go3.application.repository.exception.GameNotFoundException;
import com.go3.infrastructure.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Go3Application.class)
@RunWith(SpringRunner.class)
public class InMemoryGameRepositoryImplTest {

    @Autowired
    private IGameRepository repository;
    @Autowired
    private GameFactory gameFactory;

    @Before
    public void init() {
        repository.clear();
    }

    @Test
    public void testSuccessfulStoreAndLoadGame() {
        IGame game = gameFactory.create(20);
        repository.store(game);

        Assert.assertEquals("One game should be stored", Integer.valueOf(1), repository.count());

        IGame loadedGame = repository.load(game.getId());
        Assert.assertEquals("Games must be identical", game, loadedGame);
    }


    @Test(expected = ValidationException.class)
    public void testLoadGameInvalidId() {

        repository.load(null);

    }

    @Test(expected = GameNotFoundException.class)
    public void testLoadNonExistingGame() {

        repository.load("notfound");

    }

}