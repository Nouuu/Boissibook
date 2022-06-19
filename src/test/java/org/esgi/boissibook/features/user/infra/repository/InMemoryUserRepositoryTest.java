package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.kernel.exception.UserNotFoundException;
import org.esgi.boissibook.kernel.repository.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryUserRepositoryTest {

    private InMemoryUserRepository userRepository;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        user1 = new User(userRepository.nextId(), "name1", "email1@example.com", "password");
        user2 = new User(userRepository.nextId(), "name2", "email2@example.com", "password");
        user3 = new User(userRepository.nextId(), "name3", "email3@example.com", "password");
    }

    @Test
    @DisplayName("should save user")
    void save() {
        userRepository.save(user1);

        assertThat(userRepository.find(user1.id()))
            .isEqualTo(user1);
    }

    @Test
    @DisplayName("should count users in db")
    void count() {
        assertThat(userRepository.count())
            .isZero();

        userRepository.save(user1);

        assertThat(userRepository.count())
            .isEqualTo(1);

        userRepository.save(user2);
        userRepository.save(user3);

        assertThat(userRepository.count())
            .isEqualTo(3);
    }

    @Test
        //save 3 users and check there are 3 equals users in db
    void findAll() {
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        assertThat(userRepository.findAll())
            .hasSize(3)
            .containsOnly(user1, user2, user3);
    }

    @Test
    void find() {
        userRepository.save(user1);
        userRepository.save(user2);

        assertThat(userRepository.find(user1.id()))
            .isEqualTo(user1);
    }

    // Test find not found
    @Test
    void findNotFound() {
        var userId = userRepository.nextId();
        assertThatThrownBy(() -> userRepository.find(userId))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("User not found : " + userId.value());
    }

    @Test
    void delete() {
        userRepository.save(user1);
        userRepository.save(user2);

        userRepository.delete(user1);
        var user1Id = user1.id();
        assertThatThrownBy(() -> userRepository.find(user1Id))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("User not found : " + user1.id().value());
        assertThat(userRepository.count())
            .isEqualTo(1); //user2 is still in db
    }

    @Test
    void deleteAll() {
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        userRepository.deleteAll();
        assertThat(userRepository.findAll())
            .isEmpty();
    }


    @Test
    void findByEmail() {
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        assertThat(userRepository.findByEmail(user2.email()))
            .isEqualTo(user2);
    }

    @Test
    void findByEmailNotFound() {
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        assertThatThrownBy(() -> userRepository.findByEmail("notexisting@email.com"))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("User not found : notexisting@email.com");
    }

    @Test
    void nextId() {
        assertThat(userRepository.nextId())
            .isNotNull()
            .isInstanceOf(UserId.class);
    }
}
