package org.esgi.boissibook.features.user.domain;

import java.util.List;

public class UserQueryHandler {
    private final UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUser(String id) {
        return userRepository.find(id);
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
