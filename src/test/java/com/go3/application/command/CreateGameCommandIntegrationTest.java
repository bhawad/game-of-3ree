package com.go3.application.command;

import com.go3.Go3Application;
import com.go3.application.event.GameCreatedEvent;
import com.go3.application.repository.IGameRepository;
import com.go3.infrastructure.event.IEventPublisher;
import org.junit.Assert;
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
public class CreateGameCommandIntegrationTest {


    @MockBean
    private IEventPublisher eventPublisher;
    @Autowired
    private IGameRepository gameRepository;


    @Autowired
    private CreateGameCommand createGameCommand;


    @Before
    public void init() {
        Mockito.doNothing().when(eventPublisher).publishUserEvent(Mockito.any(), Mockito.any(GameCreatedEvent.class));
        gameRepository.clear();
    }


    @Test
    public void testExecuteCreateGame() {

        createGameCommand.execute(new CreateGameCommand.CreateGameInput("p1id", 20));

        Mockito.verify(eventPublisher).publishUserEvent(Mockito.eq("p1id"), Mockito.any(GameCreatedEvent.class));

        Assert.assertEquals("Game must be stored ", Integer.valueOf(1), gameRepository.count());
    }
}