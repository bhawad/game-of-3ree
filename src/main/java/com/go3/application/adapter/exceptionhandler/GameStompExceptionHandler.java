package com.go3.application.adapter.exceptionhandler;


import com.go3.infrastructure.event.IEventPublisher;
import com.go3.infrastructure.exception.GameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.security.Principal;

@ControllerAdvice
@Slf4j
public class GameStompExceptionHandler {


    private final IEventPublisher eventPublisher;

    @Autowired
    public GameStompExceptionHandler(IEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @MessageExceptionHandler(GameException.class)
    public void handleGameException(GameException exception, Principal principal) {
        log.debug("Game Exception Caught", exception);
        eventPublisher.publishUserErrorMessage(principal.getName(), exception.getMessage());
    }


    @MessageExceptionHandler(Exception.class)
    public void handleAnyException(Exception exception, Principal principal) {
        log.error("Server Exception caught", exception);
        eventPublisher.publishUserErrorMessage(principal.getName(), "Server Error");
    }
}
