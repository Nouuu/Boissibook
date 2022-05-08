package org.esgi.boissibook.features.user.domain;

import org.apache.commons.lang3.StringUtils;
import org.esgi.boissibook.features.user.domain.event.UserAddedEvent;
import org.esgi.boissibook.features.user.domain.event.UserDeletedEvent;
import org.esgi.boissibook.features.user.domain.event.UserUpdatedEvent;
import org.esgi.boissibook.features.user.domain.event.UsersDeletedEvent;
import org.esgi.boissibook.kernel.event.EventService;

public final class UserCommandHandler {
    private final UserRepository userRepository;
    private final EventService eventService;

    public UserCommandHandler(UserRepository userRepository, EventService eventService) {
        this.userRepository = userRepository;
        this.eventService = eventService;
    }

    public String createUser(User user) {
        String userId = userRepository.nextId();
        user.setId(userId);
        userRepository.save(user);
        eventService.publish(UserAddedEvent.of(user));
        return userId;
    }

    public void updateUser(User user) {
        var userFromRepo = userRepository.find(user.id());
        userFromRepo.setName(user.name());
        userFromRepo.setEmail(user.email());

        if (StringUtils.isNoneBlank(user.password())) {
            userFromRepo.setPassword(user.password());
        }

        userRepository.save(userFromRepo);
        eventService.publish(UserUpdatedEvent.of(user));
    }

    public void deleteUser(String userId) {
        var user = userRepository.find(userId);
        userRepository.delete(user);
        eventService.publish(UserDeletedEvent.of(user));
    }

    public void deleteAllUsers() {
        long count = userRepository.count();
        userRepository.deleteAll();
        eventService.publish(UsersDeletedEvent.of(count));
    }
}
