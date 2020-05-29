package com.go3.infrastructure.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
@Slf4j
final class EventPublisher implements IEventPublisher {

    private final SimpMessageSendingOperations simpMessageSendingOperations;


    @Override
    public final <E extends AbstractEvent> void publishUserEvent(String user, E event) {
        log.debug("Publishing Event[{}] to User[{}]", event.getType(), user);
        simpMessageSendingOperations.convertAndSendToUser(user, "queue/reply", event);
    }

    @Override
    public final <E extends AbstractEvent> void publishUserErrorMessage(String user, String errorMessage) {
        log.debug("Publishing Error[{}] to User[{}]", errorMessage, user);
        simpMessageSendingOperations.convertAndSendToUser(user, "queue/error", errorMessage);
    }

    @Override
    public final <E extends AbstractEvent> void publishGroupEvent(Collection<String> users, E event) {
        users.stream().forEach(u -> this.publishUserEvent(u, event));
    }
}
