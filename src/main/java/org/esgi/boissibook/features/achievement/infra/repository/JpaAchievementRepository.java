package org.esgi.boissibook.features.achievement.infra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAchievementRepository extends JpaRepository<AchievementEntity, String> {
    List<AchievementEntity> findByUserId(String userId);
}
