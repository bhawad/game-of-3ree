package com.go3.application.command;

import com.go3.application.event.GameFinishedEvent;
import com.go3.application.event.NextTurnEvent;
import com.go3.application.model.game.IGame;
import com.go3.application.model.game.TurnOutput;
import com.go3.application.model.player.TurnDecision;
import com.go3.application.model.player.decisionSupplier.DecisionSupplierFactory;
import com.go3.application.repository.IGameRepository;
import com.go3.infrastructure.command.ICommand;
import com.go3.infrastructure.event.IEventPublisher;
import com.go3.infrastructure.exception.GameException;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("playTurnCommand")
@RequiredArgsConstructor
public final class PlayTurnCommand implements ICommand<PlayTurnCommand.PlayTurnInput> {

    private final IEventPublisher eventPublisher;
    private final IGameRepository gameRepository;
    private final DecisionSupplierFactory decisionSupplierFactory;

    @Override
    public void execute(PlayTurnInput input) throws GameException {

        //find game
        IGame game = gameRepository.load(input.gameId);

        TurnDecision manualDecision = StringUtils.isNotBlank(input.turnDecision) ?
                TurnDecision.valueOf(input.turnDecision) : null;

        TurnOutput output = game.doTurn(input.playerId, decisionSupplierFactory.create(game.getLastNumber(), manualDecision, input.automatic));

        if (game.isGameFinished()) {

            eventPublisher.publishGroupEvent(game.getPlayers().getPlayerIds(), new GameFinishedEvent(
                    output.getTurnPlayer().getId(),
                    output.getTurnPlayer().displayName(),
                    output.getTurnResult(),
                    output.getTurnDecision().getValue(),
                    game.isWon()));

        } else {

            //announce next turn
            eventPublisher.publishGroupEvent(game.getPlayers().getPlayerIds(), new NextTurnEvent(
                    output.getNextPlayer().getId(),
                    output.getNextPlayer().displayName(),
                    output.getTurnResult(),
                    output.getTurnDecision().getValue(),
                    output.getTurnPlayer().getId(),
                    output.getTurnPlayer().displayName()
            ));

        }


    }

    @RequiredArgsConstructor
    @EqualsAndHashCode
    public static final class PlayTurnInput {
        private final String gameId;
        private final String playerId;
        private final String turnDecision;
        private final Boolean automatic;
    }
}
