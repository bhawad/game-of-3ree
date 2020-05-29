package com.go3.application.repository;


import com.go3.application.model.game.IGame;

/**
 * Interface that represents the basic operation of a game repository
 */
public interface IGameRepository {


    /**
     * Stores the given game
     *
     * @param game {@code IGame}
     */
    void store(IGame game);

    /**
     * Loads a game by the given Id
     *
     * @param id {@code String} the unique id of the game to load
     * @return {@code IGame}
     * @throws {@code com.go3.infrastructure.exception.ValidationException} if the id is empty
     *                {@code com.go3.application.repository.exception.GameNotFoundException} if the game doesn't exist by this Id
     */
    IGame load(String id);

    /**
     * Returns the count of all games stored
     *
     * @return {@code Integer}
     */
    Integer count();


    /**
     * remove all items
     */
    void clear();

}
