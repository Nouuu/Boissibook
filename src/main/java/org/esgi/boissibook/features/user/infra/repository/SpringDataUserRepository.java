package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.domain.UserRepository;
import org.esgi.boissibook.features.user.kernel.exception.UserExceptionMessage;
import org.esgi.boissibook.features.user.kernel.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public class SpringDataUserRepository implements UserRepository {
    private final JpaRepository<UserEntity, String> userRepository;

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
    public String save(User user) {
        var userEntity = UserEntityMapper.toUserEntity(user);
        return userRepository.save(userEntity).id();
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
                .map(UserEntityMapper::toUser)
                .toList();
    }

    /**
     * Find a user by id, or throw an exception if the user is not found.
     *
     * @param id The id of the user to find
     * @return User
     */
    @Override
    public User find(String id) {
        return UserEntityMapper.toUser(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("%s : %s", UserExceptionMessage.USER_NOT_FOUND, id))));
    }

    /**
     * Delete the user with the given id, or throw an exception if the user doesn't exist.
     *
     * @param user The user to be deleted.
     */
    @Override
    public void delete(User user) {
        userRepository.delete(
                userRepository.findById(user.id())
                        .orElseThrow(() -> new UserNotFoundException(String.format("%s : %s", UserExceptionMessage.USER_NOT_FOUND, user.id())))
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
    public String nextId() {
        return UUID.randomUUID().toString();
    }
}
