package org.esgi.boissibook.features.bookfile.domain.event;

import java.time.ZonedDateTime;
import org.esgi.boissibook.features.bookfile.domain.BookFile;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

public record BookFileAddedEvent(BookFile bookFile, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static BookFileAddedEvent of(BookFile bookFile) {
        return new BookFileAddedEvent(bookFile, EventId.create(), ZonedDateTime.now());
    }
}
