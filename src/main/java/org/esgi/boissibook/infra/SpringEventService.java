package org.esgi.boissibook.infra;

import org.esgi.boissibook.kernel.event.Event;
import org.esgi.boissibook.kernel.event.EventService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventService implements EventService {
    private final ApplicationEventPublisher eventPublisher;

    public SpringEventService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    @Override
    public void publish(Event event) {
        eventPublisher.publishEvent(event);
    }
}
