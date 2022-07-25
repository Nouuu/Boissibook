package org.esgi.boissibook.features.achievement.domain;

import org.esgi.boissibook.kernel.repository.DomainEntity;
import org.esgi.boissibook.kernel.repository.UserId;
import org.esgi.boissibook.kernel.repository.UserStatisticsId;

public class UserStatistics implements DomainEntity {
    private UserStatisticsId id;
    private UserId userId;
    private int uploadedBookDownloadCount;
    private int booksFinishedCount;
    private int booksAbandonedCount;
    private int userReviewCount;

    public UserStatistics(UserStatisticsId id, UserId userId, int uploadedBookDownloadCount, int booksFinishedCount, int booksAbandonedCount, int userReviewCount) {
        this.id = id;
        this.userId = userId;
        this.uploadedBookDownloadCount = uploadedBookDownloadCount;
        this.booksFinishedCount = booksFinishedCount;
        this.booksAbandonedCount = booksAbandonedCount;
        this.userReviewCount = userReviewCount;
    }

    @Override
    public UserStatisticsId id() {
        return id;
    }

    public UserStatistics setId(UserStatisticsId id) {
        this.id = id;
        return this;
    }

    public UserId userId() {
        return userId;
    }

    public UserStatistics setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public int uploadedBookDownloadCount() {
        return uploadedBookDownloadCount;
    }

    public UserStatistics setUploadedBookDownloadCount(int uploadedBookDownloadCount) {
        this.uploadedBookDownloadCount = uploadedBookDownloadCount;
        return this;
    }

    public int booksFinishedCount() {
        return booksFinishedCount;
    }

    public UserStatistics setBooksFinishedCount(int booksFinishedCount) {
        this.booksFinishedCount = booksFinishedCount;
        return this;
    }

    public int booksAbandonedCount() {
        return booksAbandonedCount;
    }

    public UserStatistics setBooksAbandonedCount(int booksAbandonedCount) {
        this.booksAbandonedCount = booksAbandonedCount;
        return this;
    }

    public int userReviewCount() {
        return userReviewCount;
    }

    public UserStatistics setUserReviewCount(int userReviewCount) {
        this.userReviewCount = userReviewCount;
        return this;
    }
}
