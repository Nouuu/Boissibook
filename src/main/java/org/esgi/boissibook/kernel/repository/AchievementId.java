package org.esgi.boissibook.kernel.repository;

import java.util.Objects;
import java.util.UUID;

public record AchievementId(String value) implements DomainId<String> {
    public static AchievementId of(String achievementId) {
        Objects.requireNonNull(achievementId, "achievementId cannot be null");
        return new AchievementId(achievementId);
    }

    public static AchievementId nextId() {
        return new AchievementId(UUID.randomUUID().toString());
    }
}
