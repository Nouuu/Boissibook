package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.domain.UserRepository;
import org.esgi.boissibook.features.user.kernel.exception.UserExceptionMessage;
import org.esgi.boissibook.features.user.kernel.exception.UserNotFoundException;
import org.esgi.boissibook.infra.repository.InMemoryRepository;
import org.esgi.boissibook.kernel.repository.UserId;

public class InMemoryUserRepository extends InMemoryRepository<User, UserId> implements UserRepository {

    @Override
    public User findByEmail(String email) {
        return super.data.values().stream()
            .filter(user -> user.email().equals(email))
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException(UserExceptionMessage.USER_NOT_FOUND.toString()));
    }

    @Override
    public UserId nextId() {
        return UserId.nextId();
    }
}
