package org.esgi.boissibook.features.achievement.infra.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user-statistics")
public class UserStatisticsEntity {
    @Id
    private String id;
    private String userId;
    private int uploadedBookDownloadCount;
    private int booksFinishedCount;
    private int booksAbandonedCount;
    private int userReviewCount;

    public UserStatisticsEntity() {
    }

    public UserStatisticsEntity(String id, String userId, int uploadedBookDownloadCount, int booksFinishedCount, int booksAbandonedCount, int userReviewCount) {
        this.id = id;
        this.userId = userId;
        this.uploadedBookDownloadCount = uploadedBookDownloadCount;
        this.booksFinishedCount = booksFinishedCount;
        this.booksAbandonedCount = booksAbandonedCount;
        this.userReviewCount = userReviewCount;
    }

    public String id() {
        return id;
    }

    public UserStatisticsEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String userId() {
        return userId;
    }

    public UserStatisticsEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public int uploadedBookDownloadCount() {
        return uploadedBookDownloadCount;
    }

    public UserStatisticsEntity setUploadedBookDownloadCount(int uploadedBookDownloadCount) {
        this.uploadedBookDownloadCount = uploadedBookDownloadCount;
        return this;
    }

    public int booksFinishedCount() {
        return booksFinishedCount;
    }

    public UserStatisticsEntity setBooksFinishedCount(int booksFinishedCount) {
        this.booksFinishedCount = booksFinishedCount;
        return this;
    }

    public int booksAbandonedCount() {
        return booksAbandonedCount;
    }

    public UserStatisticsEntity setBooksAbandonedCount(int booksAbandonedCount) {
        this.booksAbandonedCount = booksAbandonedCount;
        return this;
    }

    public int userReviewCount() {
        return userReviewCount;
    }

    public UserStatisticsEntity setUserReviewCount(int userReviewCount) {
        this.userReviewCount = userReviewCount;
        return this;
    }
}
