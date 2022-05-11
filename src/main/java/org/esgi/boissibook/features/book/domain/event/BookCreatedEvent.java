package org.esgi.boissibook.features.book.domain.event;

import java.time.ZonedDateTime;
import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

public record BookCreatedEvent(Book book, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static BookCreatedEvent of(Book book) {
        return new BookCreatedEvent(book, EventId.create(), ZonedDateTime.now());
    }
}