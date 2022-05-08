package org.esgi.boissibook.features.user.domain.event;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

import java.time.ZonedDateTime;

public record UserDeletedEvent(User user, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static UserDeletedEvent of(User user) {
        return new UserDeletedEvent(user, EventId.create(), ZonedDateTime.now());
    }
}
