package org.esgi.boissibook.features.achievement.domain;

import org.esgi.boissibook.kernel.repository.UserId;
import org.esgi.boissibook.kernel.repository.UserStatisticsId;

public class UserStatisticsCommandHandler {
    private final UserStatisticsRepository userStatisticsRepository;

    public UserStatisticsCommandHandler(UserStatisticsRepository userStatisticsRepository) {
        this.userStatisticsRepository = userStatisticsRepository;
    }

    public void createUserStatistics(UserId userId) {
        UserStatisticsId userStatisticsId = userStatisticsRepository.nextId();
        UserStatistics userStatistics = new UserStatistics(userStatisticsId, userId, 0, 0, 0, 0);
        userStatisticsRepository.save(userStatistics);
    }

    public int uploadedBookDownloaded(UserId userId) {
        var entity = userStatisticsRepository.findByUserId(userId);
        var count = entity.uploadedBookDownloadCount() + 1;
        entity.setUploadedBookDownloadCount(count);
        userStatisticsRepository.save(entity);
        return count;
    }

    public int bookFinished(UserId userId) {
        var entity = userStatisticsRepository.findByUserId(userId);
        var count = entity.booksFinishedCount() + 1;
        entity.setBooksFinishedCount(count);
        userStatisticsRepository.save(entity);
        return count;
    }

    public int bookAbandoned(UserId userId) {
        var entity = userStatisticsRepository.findByUserId(userId);
        var count = entity.booksAbandonedCount() + 1;
        entity.setBooksAbandonedCount(count);
        userStatisticsRepository.save(entity);
        return count;
    }

    public int userReviewed(UserId userId) {
        var entity = userStatisticsRepository.findByUserId(userId);
        var count = entity.userReviewCount() + 1;
        entity.setUserReviewCount(count);
        userStatisticsRepository.save(entity);
        return count;
    }
}
