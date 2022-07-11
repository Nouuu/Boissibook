package org.esgi.boissibook.features.achievement.domain;

import java.time.ZonedDateTime;
import java.util.Arrays;
import org.esgi.boissibook.kernel.repository.UserId;

public class AchievementCommandHandler {
    private final AchievementRepository achievementRepository;

    public AchievementCommandHandler(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }


    public void addUploadedBooksDownloadedAchievement(UserId userId, int uploadedBooksDownloaded) {
        saveBookOnLevelReach(uploadedBooksDownloaded, userId, "Uploaded Books' Downloads", "You have downloaded " + uploadedBooksDownloaded + " books");
    }

    public void addBooksFinishedAchievement(UserId userId, int booksFinished) {
        saveBookOnLevelReach(booksFinished, userId, "Books Finished", "You have finished " + booksFinished + " books");
    }

    public void addBooksAbandonedAchievement(UserId userId, int booksAbandoned) {
        saveBookOnLevelReach(booksAbandoned, userId, "Books Abandoned", "You have abandoned " + booksAbandoned + " books");
    }

    public void addUserReviewedAchievement(UserId userId, int userReviewed) {
        saveBookOnLevelReach(userReviewed, userId, "User Reviews", "You have reviewed " + userReviewed + " books");
    }

    private void saveBookOnLevelReach(int count, UserId userId, String title, String description) {
        Arrays.stream(AchievementLevel.values())
            .filter(level -> level.neededCount() == count)
            .findFirst()
            .ifPresent(level -> {
                var entity = new Achievement()
                    .setUserId(userId)
                    .setTitle(title)
                    .setDescription(description)
                    .setAchievedAt(ZonedDateTime.now())
                    .setLevel(level);
                achievementRepository.save(entity);
            });
    }
}
