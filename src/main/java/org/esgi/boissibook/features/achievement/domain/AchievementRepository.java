package org.esgi.boissibook.features.achievement.domain;

import java.util.List;
import org.esgi.boissibook.kernel.repository.AchievementId;
import org.esgi.boissibook.kernel.repository.Repository;
import org.esgi.boissibook.kernel.repository.UserId;

public interface AchievementRepository extends Repository<Achievement, AchievementId> {
    List<Achievement> findByUserId(UserId userId);
}
