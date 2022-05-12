package org.esgi.boissibook.features.readlist.domain.event;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

import java.time.ZonedDateTime;

public record UserAddBookReviewEvent(BookReview bookReview,EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static UserAddBookReviewEvent of(BookReview bookReview) {
        return new UserAddBookReviewEvent(bookReview, EventId.create(), ZonedDateTime.now());
    }
}
