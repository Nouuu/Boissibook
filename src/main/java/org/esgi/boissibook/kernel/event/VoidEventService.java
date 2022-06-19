package org.esgi.boissibook.kernel.event;

public class VoidEventService implements EventService {
    @Override
    public void publish(Event event) {
        System.out.println(event);
    }
}
