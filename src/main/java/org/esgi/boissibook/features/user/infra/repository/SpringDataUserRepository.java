package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.domain.UserRepository;
import org.esgi.boissibook.features.user.infra.UserMapper;
import org.esgi.boissibook.features.user.kernel.exception.UserExceptionMessage;
import org.esgi.boissibook.features.user.kernel.exception.UserNotFoundException;
import org.esgi.boissibook.kernel.repository.UserId;

import java.util.List;

public class SpringDataUserRepository implements UserRepository {

    private static final String FORMATTED_EXCEPTION = "%s %s";
    private final JPAUserRepository userRepository;

    public SpringDataUserRepository(JPAUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * We create a new UserEntity from the User, save it, and return the id.
     *
     * @param user The user object that is passed in from the controller.
     * @return The id of the user that was saved.
     */
    @Override
    public UserId save(User user) {
        var userEntity = UserMapper.toUserEntity(user);
        userRepository.save(userEntity);
        return user.id();
    }

    /**
     * Returns the number of users in the database
     *
     * @return The number of users in the database.
     */
    @Override
    public long count() {
        return userRepository.count();
    }

    /**
     * We're using the `findAll()` function from the `UserRepository` to get a list of `UserEntity` objects, then we
     * convert the list into a list of `User` object,
     *
     * @return A list of users
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll()
            .stream()
            .map(UserMapper::toUser)
            .toList();
    }

    /**
     * Find a user by id, or throw an exception if the user is not found.
     *
     * @param id The id of the user to find
     * @return User
     */
    @Override
    public User find(UserId id) {
        return UserMapper.toUser(userRepository.findById(id.value())
            .orElseThrow(() -> new UserNotFoundException(String.format(FORMATTED_EXCEPTION, UserExceptionMessage.USER_NOT_FOUND, id.value()))));
    }

    /**
     * Delete the user with the given id, or throw an exception if the user doesn't exist.
     *
     * @param user The user to be deleted.
     */
    @Override
    public void delete(User user) {
        userRepository.delete(
            userRepository.findById(user.id().value())
                .orElseThrow(() -> new UserNotFoundException(String.format(FORMATTED_EXCEPTION, UserExceptionMessage.USER_NOT_FOUND, user.id())))
        );
    }

    /**
     * It deletes all the users in the database.
     */
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public UserId nextId() {
        return UserId.nextId();
    }

    @Override
    public User findByEmail(String email) {
        return UserMapper.toUser(userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException(String.format(FORMATTED_EXCEPTION, UserExceptionMessage.USER_NOT_FOUND, email))));
    }
}
