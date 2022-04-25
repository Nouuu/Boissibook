package org.esgi.boissibook.kernel.event;

import java.util.UUID;

public record EventId(String value) {
    public static EventId create() {
        return new EventId(UUID.randomUUID().toString());
    }
}
