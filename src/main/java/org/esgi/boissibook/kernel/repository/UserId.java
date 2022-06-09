package org.esgi.boissibook.kernel.repository;

import java.util.Objects;
import java.util.UUID;

public record UserId(String value) implements DomainId<String> {

    public static UserId of(String userId) {
        Objects.requireNonNull(userId, "userId cannot be null");
        return new UserId(userId);
    }

    public static UserId nextId() {
        return new UserId(UUID.randomUUID().toString());
    }
}
