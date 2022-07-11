package org.esgi.boissibook.features.achievement.infra;

import org.esgi.boissibook.features.achievement.domain.Achievement;
import org.esgi.boissibook.features.achievement.infra.repository.AchievementEntity;
import org.esgi.boissibook.features.achievement.infra.web.response.AchievementResponse;
import org.esgi.boissibook.kernel.repository.AchievementId;
import org.esgi.boissibook.kernel.repository.UserId;

public final class AchievementMapper {

    private AchievementMapper() {
    }

    public static AchievementResponse mapAchievementToAchievementResponse(Achievement achievement) {
        return new AchievementResponse(achievement.id().value(), achievement.userId().value(), achievement.title(), achievement.description(), achievement.achievedAt(), achievement.level());
    }

    public static Achievement mapAchievementEntityToAchievement(AchievementEntity entity) {
        return new Achievement(new AchievementId(entity.id()), new UserId(entity.userId()), entity.title(), entity.description(), entity.achievedAt(), entity.level());
    }

    public static AchievementEntity mapAchievementToAchievementEntity(Achievement achievement) {
        return new AchievementEntity(achievement.id().value(), achievement.userId().value(), achievement.title(), achievement.description(), achievement.achievedAt(), achievement.level());
    }
}
