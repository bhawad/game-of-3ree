package com.go3.application.adapter.stomp;

import com.go3.application.adapter.stomp.dto.AddGamePlayerDto;
import com.go3.application.adapter.stomp.dto.CreateGameDto;
import com.go3.application.adapter.stomp.dto.PlayTurnDto;
import com.go3.application.command.AddPlayerCommand;
import com.go3.application.command.CreateGameCommand;
import com.go3.application.command.PlayTurnCommand;
import com.go3.infrastructure.command.ICommand;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@MessageMapping("/game")
@RequiredArgsConstructor
public class GameStompController {

    private final ICommand addPlayerCommand;
    private final ICommand createGameCommand;
    private final ICommand playTurnCommand;

    @MessageMapping("/Create.New")
    public void createNewGame(CreateGameDto createGameDto, Principal principal) {

        createGameCommand.execute(new CreateGameCommand.CreateGameInput(principal.getName(), createGameDto.getInitialNumber()));

    }

    @MessageMapping("/Player.Add")
    public void addGamePlayer(AddGamePlayerDto addGamePlayerDto, Principal principal) {

        addPlayerCommand.execute(new AddPlayerCommand.AddPlayerInput(principal.getName(),
                addGamePlayerDto.getGameId(),
                addGamePlayerDto.getPlayerDisplayName()));
    }

    @MessageMapping("/Player.Play")
    public void playTurn(PlayTurnDto playTurnDto, Principal principal) {

        playTurnCommand.execute(new PlayTurnCommand.PlayTurnInput(playTurnDto.getGameId(), principal.getName(),
                playTurnDto.getTurnDecision(), playTurnDto.getAutomatic()));
    }
}
