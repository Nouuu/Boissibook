package org.esgi.boissibook.features.book.domain.event;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

import java.time.ZonedDateTime;

public record BookDeletedEvent(Book book, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static BookDeletedEvent of(Book book) {
        return new BookDeletedEvent(book, EventId.create(), ZonedDateTime.now());
    }
}
