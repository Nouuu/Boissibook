package org.esgi.boissibook.features.user.domain.event;

import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

import java.time.ZonedDateTime;

public record UsersDeletedEvent(long count, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static UsersDeletedEvent of(long count) {
        return new UsersDeletedEvent(count, EventId.create(), ZonedDateTime.now());
    }
}
