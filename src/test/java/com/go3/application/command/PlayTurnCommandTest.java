package com.go3.application.command;

import com.go3.Go3Application;
import com.go3.application.event.GameFinishedEvent;
import com.go3.application.event.NextTurnEvent;
import com.go3.application.model.game.GameFactory;
import com.go3.application.model.game.IGame;
import com.go3.application.model.player.IPlayer;
import com.go3.application.model.player.PlayerFactory;
import com.go3.application.model.player.TurnDecision;
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
public class PlayTurnCommandTest {


    @MockBean
    private IEventPublisher eventPublisher;
    @Autowired
    private IGameRepository gameRepository;
    @Autowired
    private GameFactory gameFactory;
    @Autowired
    private PlayerFactory playerFactory;
    @Autowired
    private PlayTurnCommand testCommand;


    @Before
    public void init() {
        Mockito.doNothing().when(eventPublisher).publishGroupEvent(Mockito.any(), Mockito.any());

        gameRepository.clear();
    }


    @Test
    public void testGamePlayExecute() {

        //setup
        IGame testGame = gameFactory.create(9);
        gameRepository.store(testGame);

        IPlayer firstPlayer = playerFactory.create("pid1", "p1name");
        IPlayer secondPlayer = playerFactory.create("pid2", "p2name");

        testGame.getPlayers().addGamePlayer(firstPlayer);
        testGame.getPlayers().addGamePlayer(secondPlayer);

        //execute and verify
        testCommand.execute(new PlayTurnCommand.PlayTurnInput(testGame.getId(), firstPlayer.getId(), "ZERO", false));

        Mockito.verify(eventPublisher).publishGroupEvent(testGame.getPlayers().getPlayerIds(), new NextTurnEvent(
                secondPlayer.getId(),
                secondPlayer.displayName(),
                3,
                TurnDecision.ZERO.getValue(),
                firstPlayer.getId(),
                firstPlayer.displayName()
        ));


        //play last round
        testCommand.execute(new PlayTurnCommand.PlayTurnInput(testGame.getId(), secondPlayer.getId(), "ZERO", false));

        Mockito.verify(eventPublisher).publishGroupEvent(testGame.getPlayers().getPlayerIds(), new GameFinishedEvent(
                secondPlayer.getId(),
                secondPlayer.displayName(),
                1,
                TurnDecision.ZERO.getValue(),
                true));
    }


}