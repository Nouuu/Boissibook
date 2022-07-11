package org.esgi.boissibook.features.readlist.domain.event;

import java.time.ZonedDateTime;
import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

public record ReviewStatusChangedEvent(BookReview bookReview, EventId eventId,
                                       ZonedDateTime occurredDate) implements Event {
    public static ReviewStatusChangedEvent of(BookReview bookReview) {
        return new ReviewStatusChangedEvent(bookReview, EventId.create(), ZonedDateTime.now());
    }
}
