package org.esgi.boissibook.features.achievement.infra.repository;

import java.util.Optional;
import org.esgi.boissibook.kernel.repository.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserStatisticsRepository extends JpaRepository<UserStatisticsEntity, String> {
    Optional<UserStatisticsEntity> findByUserId(String userId);
}
