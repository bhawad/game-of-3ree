package com.go3.application.command;

import com.go3.Go3Application;
import com.go3.application.event.NextTurnEvent;
import com.go3.application.event.PlayerAddedEvent;
import com.go3.application.model.game.GameFactory;
import com.go3.application.model.game.IGame;
import com.go3.application.model.player.IPlayer;
import com.go3.application.model.player.PlayerFactory;
import com.go3.application.repository.IGameRepository;
import com.go3.infrastructure.event.IEventPublisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Go3Application.class)
@RunWith(SpringRunner.class)
public class AddPlayerCommandIntegrationTest {

    @MockBean
    private IEventPublisher eventPublisher;
    @Autowired
    private IGameRepository gameRepository;
    @Autowired
    private GameFactory gameFactory;
    @Autowired
    private AddPlayerCommand addPlayerCommand;
    @Autowired
    private PlayerFactory playerFactory;


    @Before
    public void init() {

        Mockito.doNothing().when(eventPublisher).publishGroupEvent(Mockito.any(), Mockito.any(PlayerAddedEvent.class));
        Mockito.doNothing().when(eventPublisher).publishGroupEvent(Mockito.any(), Mockito.any(NextTurnEvent.class));

        gameRepository.clear();
    }


    @Test
    public void testAddPlayerWithPlayersReady() {

        //setup
        IGame testGame = gameFactory.create(9);
        gameRepository.store(testGame);

        IPlayer firstPlayer = playerFactory.create("pid1", "p1name");
        IPlayer secondPlayer = playerFactory.create("pid2", "p2name");

        //execute and verify
        addPlayerCommand.execute(new AddPlayerCommand.AddPlayerInput(firstPlayer.getId(), testGame.getId(), firstPlayer.displayName()));

        Mockito.verify(eventPublisher).publishGroupEvent(testGame.getPlayers().getPlayerIds(),
                new PlayerAddedEvent(firstPlayer.displayName(), firstPlayer.getId(), testGame.getId(), testGame.getLastNumber()));

        //add second player
        addPlayerCommand.execute(new AddPlayerCommand.AddPlayerInput(secondPlayer.getId(), testGame.getId(), secondPlayer.displayName()));

        Mockito.verify(eventPublisher).publishGroupEvent(testGame.getPlayers().getPlayerIds(),
                new PlayerAddedEvent(secondPlayer.displayName(), secondPlayer.getId(), testGame.getId(), testGame.getLastNumber()));

        Mockito.verify(eventPublisher).publishGroupEvent(testGame.getPlayers().getPlayerIds(),
                new NextTurnEvent(secondPlayer.getId(), secondPlayer.displayName(),
                        testGame.getLastNumber(),
                        0,
                        firstPlayer.getId(),
                        firstPlayer.displayName()
                ));
    }
}