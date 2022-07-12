package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.domain.UserRepository;
import org.esgi.boissibook.features.user.kernel.exception.UserExceptionMessage;
import org.esgi.boissibook.features.user.kernel.exception.UserNotFoundException;
import org.esgi.boissibook.infra.repository.InMemoryRepository;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.repository.UserId;

import java.util.Objects;

public class InMemoryUserRepository extends InMemoryRepository<User, UserId> implements UserRepository {
    private static final String FORMATTED_EXCEPTION = "%s : %s";
    private static final String ID_NOT_NULL = "Id can't be null";

    @Override
    public User findByEmail(String email) {
        return data.values().stream()
            .filter(user -> user.email().equals(email))
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException(String.format(FORMATTED_EXCEPTION, UserExceptionMessage.USER_NOT_FOUND, email)));
    }

    @Override
    public UserId nextId() {
        return UserId.nextId();
    }

    @Override
    public User find(UserId id) throws NotFoundException {
        var result = data.get(Objects.requireNonNull(id, ID_NOT_NULL).value());
        if (result == null) {
            throw new UserNotFoundException(String.format(FORMATTED_EXCEPTION, UserExceptionMessage.USER_NOT_FOUND, id.value()));
        }
        return result;
    }

    @Override
    public void delete(User entity) throws NotFoundException {
        if (data.remove(Objects.requireNonNull(entity.id(), ID_NOT_NULL).value()) == null) {
            throw new UserNotFoundException(String.format(FORMATTED_EXCEPTION, UserExceptionMessage.USER_NOT_FOUND, entity.id().value()));
        }
    }
}
