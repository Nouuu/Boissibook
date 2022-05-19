package org.esgi.boissibook.features.book_file.domain.event;

import java.time.ZonedDateTime;
import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

public record BookFileDeletedEvent(BookFile bookFile, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static BookFileDeletedEvent of(BookFile bookFile) {
        return new BookFileDeletedEvent(bookFile, EventId.create(), ZonedDateTime.now());
    }
}
