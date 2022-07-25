package org.esgi.boissibook.features.achievement.domain;

import java.util.List;
import org.esgi.boissibook.kernel.repository.UserId;

public class AchievementQueryHandler {
    private final AchievementRepository achievementRepository;

    public AchievementQueryHandler(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public List<Achievement> getUserAchievements(UserId userId){
        return achievementRepository.findByUserId(userId);
    }
}
