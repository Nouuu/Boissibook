package org.esgi.boissibook.features.achievement.infra;

import org.esgi.boissibook.features.achievement.domain.UserStatistics;
import org.esgi.boissibook.features.achievement.infra.repository.UserStatisticsEntity;
import org.esgi.boissibook.kernel.repository.UserId;
import org.esgi.boissibook.kernel.repository.UserStatisticsId;

public final class UserStatisticsMapper {

    private UserStatisticsMapper() {
    }

    public static UserStatistics mapUserStatisticsEntityToUserStatistics(UserStatisticsEntity entity) {
        return new UserStatistics(new UserStatisticsId(entity.id()), new UserId(entity.userId()), entity.uploadedBookDownloadCount(), entity.booksFinishedCount(), entity.booksAbandonedCount(), entity.userReviewCount());
    }

    public static UserStatisticsEntity mapUserStatisticsToUserStatisticsEntity(UserStatistics userStatistics) {
        return new UserStatisticsEntity(userStatistics.id().value(), userStatistics.userId().value(), userStatistics.uploadedBookDownloadCount(), userStatistics.booksFinishedCount(), userStatistics.booksAbandonedCount(), userStatistics.userReviewCount());
    }
}
