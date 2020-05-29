package com.go3.infrastructure.event;

import java.util.Collection;

public interface IEventPublisher {


    <E extends AbstractEvent> void publishUserEvent(String user, E event);

    <E extends AbstractEvent> void publishUserErrorMessage(String user, String errorMessage);

    <E extends AbstractEvent> void publishGroupEvent(Collection<String> users, E event);
}
