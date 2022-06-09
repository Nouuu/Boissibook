package org.esgi.boissibook.kernel.event;

import java.time.ZonedDateTime;

public interface Event {
    EventId eventId();

    ZonedDateTime occurredDate();
}
