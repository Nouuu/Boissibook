package org.esgi.boissibook.features.user.domain;

import org.esgi.boissibook.features.user.domain.event.UserAddedEvent;
import org.esgi.boissibook.kernel.event.EventService;

import java.util.List;

public class UserCommandHandler {
    private final UserRepository userRepository;
    private final EventService eventService;

    public UserCommandHandler(UserRepository userRepository, EventService eventService) {
        this.userRepository = userRepository;
        this.eventService = eventService;
    }

    public String createUser(User user) {
        String userId = userRepository.save(user);
        user.setId(userId);
        eventService.publish(UserAddedEvent.of(user));
        return userId;
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

}
