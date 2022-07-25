package org.esgi.boissibook.kernel.repository;

import java.util.Objects;
import java.util.UUID;

public record UserStatisticsId(String value) implements DomainId<String> {
    public static UserStatisticsId of(String userStatisticsId) {
        Objects.requireNonNull(userStatisticsId, "userStatisticsId cannot be null");
        return new UserStatisticsId(userStatisticsId);
    }

    public static UserStatisticsId nextId() {
        return new UserStatisticsId(UUID.randomUUID().toString());
    }
}
