package org.esgi.boissibook.features.achievement.infra;

import org.esgi.boissibook.features.achievement.domain.AchievementCommandHandler;
import org.esgi.boissibook.features.achievement.domain.UserStatisticsCommandHandler;
import org.esgi.boissibook.features.book_file.domain.event.BookFileDownloadedEvent;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.event.ReviewStatusChangedEvent;
import org.esgi.boissibook.features.readlist.domain.event.UserAddBookReviewEvent;
import org.esgi.boissibook.features.user.domain.event.UserAddedEvent;
import org.springframework.context.event.EventListener;

public class SpringUserStatisticsEventListener {
    private final UserStatisticsCommandHandler userStatisticsCommandHandler;
    private final AchievementCommandHandler achievementCommandHandler;

    public SpringUserStatisticsEventListener(UserStatisticsCommandHandler userStatisticsCommandHandler, AchievementCommandHandler achievementCommandHandler) {
        this.userStatisticsCommandHandler = userStatisticsCommandHandler;
        this.achievementCommandHandler = achievementCommandHandler;
    }

    @EventListener
    public void handleBookFileDownloaded(BookFileDownloadedEvent event) {
        var count = userStatisticsCommandHandler.uploadedBookDownloaded(event.bookFile().userId());
        achievementCommandHandler.addUploadedBooksDownloadedAchievement(event.bookFile().userId(), count);
    }

    @EventListener
    public void handleReviewStatusChanged(ReviewStatusChangedEvent event) {
        var readingStatus = event.bookReview().getReadingStatus();
        if (readingStatus == ReadingStatus.COMPLETED) {
            var count = userStatisticsCommandHandler.bookFinished(event.bookReview().getUserId());
            achievementCommandHandler.addUploadedBooksDownloadedAchievement(event.bookReview().getUserId(), count);
        } else if (readingStatus == ReadingStatus.DROPPED) {
            var count = userStatisticsCommandHandler.bookAbandoned(event.bookReview().getUserId());
            achievementCommandHandler.addUploadedBooksDownloadedAchievement(event.bookReview().getUserId(), count);
        }
    }

    @EventListener
    public void handleBookReviewed(UserAddBookReviewEvent event) {
        var count = userStatisticsCommandHandler.userReviewed(event.bookReview().getUserId());
        achievementCommandHandler.addUploadedBooksDownloadedAchievement(event.bookReview().getUserId(), count);
    }

    @EventListener
    public void handleUserAdded(UserAddedEvent event) {
        userStatisticsCommandHandler.createUserStatistics(event.user().id());
    }
}
