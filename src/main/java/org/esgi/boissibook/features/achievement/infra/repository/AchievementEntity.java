package org.esgi.boissibook.features.achievement.infra.repository;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.esgi.boissibook.features.achievement.domain.AchievementLevel;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "book")
public class AchievementEntity {
    @Id
    private String id;
    private String userId;
    private String title;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    private ZonedDateTime achievedAt;
    @Enumerated(EnumType.STRING)
    private AchievementLevel level;

    public AchievementEntity() {
    }

    public AchievementEntity(String id, String userId, String title, String description, ZonedDateTime achievedAt, AchievementLevel level) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.achievedAt = achievedAt;
        this.level = level;
    }

    public String id() {
        return id;
    }

    public AchievementEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String userId() {
        return userId;
    }

    public AchievementEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String title() {
        return title;
    }

    public AchievementEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String description() {
        return description;
    }

    public AchievementEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public ZonedDateTime achievedAt() {
        return achievedAt;
    }

    public AchievementEntity setAchievedAt(ZonedDateTime achievedAt) {
        this.achievedAt = achievedAt;
        return this;
    }

    public AchievementLevel level() {
        return level;
    }

    public AchievementEntity setLevel(AchievementLevel level) {
        this.level = level;
        return this;
    }
}
