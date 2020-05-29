package com.go3.infrastructure.event;

import java.io.Serializable;

public abstract class AbstractEvent implements Serializable {

    private final String type;

    public AbstractEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
