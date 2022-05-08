package org.esgi.boissibook.features.bookfile.domain.event;

import java.time.ZonedDateTime;
import org.esgi.boissibook.features.bookfile.domain.BookFile;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

public record BookFileDeletedEvent(BookFile bookFile, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static BookFileDeletedEvent of(BookFile bookFile) {
        return new BookFileDeletedEvent(bookFile, EventId.create(), ZonedDateTime.now());
    }
}
