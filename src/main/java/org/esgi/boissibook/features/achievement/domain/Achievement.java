package org.esgi.boissibook.features.achievement.domain;

import java.time.ZonedDateTime;
import org.esgi.boissibook.kernel.repository.AchievementId;
import org.esgi.boissibook.kernel.repository.DomainEntity;
import org.esgi.boissibook.kernel.repository.UserId;

public class Achievement implements DomainEntity {
    private AchievementId id;
    private UserId userId;
    private String title;
    private String description;
    private ZonedDateTime achievedAt;
    private AchievementLevel level;

    public Achievement(AchievementId id, UserId userId, String title, String description, ZonedDateTime achievedAt, AchievementLevel level) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.achievedAt = achievedAt;
        this.level = level;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public ZonedDateTime achievedAt() {
        return achievedAt;
    }

    @Override
    public AchievementId id() {
        return id;
    }

    public Achievement setId(AchievementId id) {
        this.id = id;
        return this;
    }

    public Achievement setTitle(String title) {
        this.title = title;
        return this;
    }

    public Achievement setDescription(String description) {
        this.description = description;
        return this;
    }

    public Achievement setAchievedAt(ZonedDateTime achievedAt) {
        this.achievedAt = achievedAt;
        return this;
    }

    public UserId userId() {
        return userId;
    }

    public Achievement setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public AchievementLevel level() {
        return level;
    }

    public Achievement setLevel(AchievementLevel level) {
        this.level = level;
        return this;
    }
}
