package org.esgi.boissibook.features.achievement.domain;

import org.esgi.boissibook.kernel.repository.Repository;
import org.esgi.boissibook.kernel.repository.UserId;
import org.esgi.boissibook.kernel.repository.UserStatisticsId;

public interface UserStatisticsRepository extends Repository<UserStatistics, UserStatisticsId> {
    UserStatistics findByUserId(UserId userId);
}
