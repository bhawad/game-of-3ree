package com.go3.application.adapter.stomp;

import com.go3.Go3Application;
import com.go3.application.adapter.stomp.dto.AddGamePlayerDto;
import com.go3.application.adapter.stomp.dto.CreateGameDto;
import com.go3.application.adapter.stomp.dto.PlayTurnDto;
import com.go3.application.command.AddPlayerCommand;
import com.go3.application.command.CreateGameCommand;
import com.go3.application.command.PlayTurnCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Go3Application.class)
@RunWith(SpringRunner.class)
public class GameStompControllerIntegrationTest {

    @MockBean
    private AddPlayerCommand addPlayerCommand;
    @MockBean
    private CreateGameCommand createGameCommand;
    @MockBean
    private PlayTurnCommand playTurnCommand;

    @Autowired
    private GameStompController testController;

    @Test
    public void testCreateGame() {

        String playerId = "userId";

        testController.createNewGame(new CreateGameDto(20), () -> playerId);

        Mockito.verify(createGameCommand).execute(new CreateGameCommand.CreateGameInput(playerId, 20));
    }

    @Test
    public void testAddPlayer() {

        String playerId = "playerId";
        String gameId = "gameId";
        String playerName = "playerName";

        testController.addGamePlayer(new AddGamePlayerDto(gameId, playerName), () -> playerId);

        Mockito.verify(addPlayerCommand).execute(new AddPlayerCommand.AddPlayerInput(playerId, gameId, playerName));
    }


    @Test
    public void testPlayTurn() {

        String playerId = "playerId";
        String gameId = "gameId";
        String decision = "ZERO";

        testController.playTurn(new PlayTurnDto(gameId, decision, false), () -> playerId);

        Mockito.verify(playTurnCommand).execute(new PlayTurnCommand.PlayTurnInput(gameId, playerId, decision, false));
    }

}