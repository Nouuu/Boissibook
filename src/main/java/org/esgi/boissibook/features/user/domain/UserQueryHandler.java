package org.esgi.boissibook.features.user.domain;

import org.esgi.boissibook.kernel.repository.UserId;

import java.util.List;

public final class UserQueryHandler {
    private final UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(UserId id) {
        return userRepository.find(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public long countUsers() {
        return userRepository.count();
    }
}
