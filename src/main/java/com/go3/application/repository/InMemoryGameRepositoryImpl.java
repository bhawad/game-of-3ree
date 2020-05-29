package com.go3.application.repository;

import com.go3.application.model.game.IGame;
import com.go3.application.repository.exception.GameNotFoundException;
import com.go3.infrastructure.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
final class InMemoryGameRepositoryImpl implements IGameRepository {

    private final Set<IGame> games;

    InMemoryGameRepositoryImpl() {
        this.games = new HashSet<>();
    }

    @Override
    public void store(IGame game) {
        games.add(game);
    }


    @Override
    public IGame load(String id) {
        if (StringUtils.isBlank(id)) {
            throw new ValidationException("Game id is invalid");
        }
        return games.stream().filter(game -> game.getId().equals(id)).findFirst()
                .orElseThrow(() -> new GameNotFoundException("The selected game doesn't exist"));
    }

    @Override
    public Integer count() {
        return games.size();
    }

    @Override
    public void clear() {
        games.clear();
    }
}
