package org.esgi.boissibook.features.book_file.domain.event;

import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

import java.time.ZonedDateTime;

public record BookFileDeletedEvent(BookFile bookFile, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static BookFileDeletedEvent of(BookFile bookFile) {
        return new BookFileDeletedEvent(bookFile, EventId.create(), ZonedDateTime.now());
    }
}
