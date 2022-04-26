package org.esgi.boissibook.features.user.domain;

import org.esgi.boissibook.features.user.domain.event.UserAddedEvent;
import org.esgi.boissibook.kernel.event.EventService;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private final EventService eventService;

    public UserService(UserRepository userRepository, EventService eventService) {
        this.userRepository = userRepository;
        this.eventService = eventService;
    }

    /**
     * "Create a user and publish an event."
     * <p>
     * The function is doing two things:
     * <p>
     * 1. Create a user
     * 2. Publish an event
     *
     * @param user The user object that is being created.
     * @return The userId is being returned.
     */
    public String createUser(User user) {
        String userId = userRepository.save(user);
        user.setId(userId);
        eventService.publish(UserAddedEvent.of(user));
        return userId;
    }


    /**
     * This function returns a user from the user repository.
     *
     * @param id The id of the user to retrieve.
     * @return A user object
     */
    public User getUser(String id) {
        return userRepository.find(id);
    }

    /**
     * Update a user in the database.
     *
     * @param user The user object that is to be updated.
     */
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * Get all users from the database and return them as a list.
     *
     * @return A list of all users in the database.
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
