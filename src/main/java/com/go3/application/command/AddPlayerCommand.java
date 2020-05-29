package com.go3.application.command;

import com.go3.application.event.NextTurnEvent;
import com.go3.application.event.PlayerAddedEvent;
import com.go3.application.model.game.IGame;
import com.go3.application.model.player.IPlayer;
import com.go3.application.model.player.PlayerFactory;
import com.go3.application.repository.IGameRepository;
import com.go3.infrastructure.command.ICommand;
import com.go3.infrastructure.event.IEventPublisher;
import com.go3.infrastructure.exception.GameException;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("addPlayerCommand")
@RequiredArgsConstructor
public final class AddPlayerCommand implements ICommand<AddPlayerCommand.AddPlayerInput> {

    private final IEventPublisher eventPublisher;
    private final IGameRepository gameRepository;
    private final PlayerFactory playerFactory;


    @Override
    public void execute(AddPlayerInput input) throws GameException {

        //find game
        IGame game = gameRepository.load(input.gameId);

        //create player
        IPlayer player = playerFactory.create(input.playerId, input.playerDisplayName);

        //add
        game.getPlayers().addGamePlayer(player);

        //publish player added
        eventPublisher.publishGroupEvent(game.getPlayers().getPlayerIds(),
                new PlayerAddedEvent(player.displayName(), player.getId(), game.getId(), game.getLastNumber()));

        //check if players are ready, announce a turn
        if (game.getPlayers().arePlayersReady()) {

            IPlayer startPlayer = game.getPlayers().getNextPlayer();

            //when players are ready, switch the turn to the first player that should play after start
            game.getPlayers().switchToNextPlayer();

            IPlayer nextPlayer = game.getPlayers().getNextPlayer();

            eventPublisher.publishGroupEvent(game.getPlayers().getPlayerIds(),
                    new NextTurnEvent(nextPlayer.getId(), nextPlayer.displayName(),
                            game.getLastNumber(),
                            0,
                            startPlayer.getId(),
                            startPlayer.displayName()
                    ));
        }
    }

    @RequiredArgsConstructor
    @EqualsAndHashCode
    public static final class AddPlayerInput {
        private final String playerId;
        private final String gameId;
        private final String playerDisplayName;
    }
}
