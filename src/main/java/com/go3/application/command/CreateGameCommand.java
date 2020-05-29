package com.go3.application.command;

import com.go3.application.event.GameCreatedEvent;
import com.go3.application.model.game.GameFactory;
import com.go3.application.model.game.IGame;
import com.go3.application.repository.IGameRepository;
import com.go3.infrastructure.command.ICommand;
import com.go3.infrastructure.event.IEventPublisher;
import com.go3.infrastructure.exception.GameException;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("createGameCommand")
@RequiredArgsConstructor
public final class CreateGameCommand implements ICommand<CreateGameCommand.CreateGameInput> {

    private final IEventPublisher eventPublisher;
    private final IGameRepository gameRepository;
    private final GameFactory gameFactory;


    @Override
    public void execute(CreateGameInput input) throws GameException {

        //create a game
        IGame game = gameFactory.create(input.firstNumber);

        //store
        gameRepository.store(game);

        //publish game created
        eventPublisher.publishUserEvent(input.playerId, new GameCreatedEvent(game.getId(), game.getLastNumber()));
    }

    @RequiredArgsConstructor
    @EqualsAndHashCode
    public static final class CreateGameInput {
        private final String playerId;
        private final Integer firstNumber;
    }
}
