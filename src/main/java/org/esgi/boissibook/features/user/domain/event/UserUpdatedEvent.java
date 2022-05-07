package org.esgi.boissibook.features.user.domain.event;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventId;

import java.time.ZonedDateTime;

public record UserUpdatedEvent(User user, EventId eventId, ZonedDateTime occurredDate) implements Event {
    public static UserUpdatedEvent of(User user) {
        return new UserUpdatedEvent(user, EventId.create(), ZonedDateTime.now());
    }
}
