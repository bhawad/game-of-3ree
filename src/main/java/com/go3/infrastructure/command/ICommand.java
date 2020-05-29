package com.go3.infrastructure.command;

import com.go3.infrastructure.exception.GameException;

/**
 * Interface that represents a game command
 *
 * @param <I>
 */
public interface ICommand<I> {


    /**
     * Execute method to be called for this command to execute
     *
     * @param input Command Input
     */
    void execute(I input) throws GameException;

}
