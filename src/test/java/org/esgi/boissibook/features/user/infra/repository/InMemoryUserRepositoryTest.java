package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.kernel.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryUserRepositoryTest {

    private InMemoryUserRepository inMemoryUserRepository;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() {
        inMemoryUserRepository = new InMemoryUserRepository();
        user1 = new User(inMemoryUserRepository.nextId(), "name", "email@example.com", "password");
        user2 = new User(inMemoryUserRepository.nextId(), "name", "email@example.com", "password");
        user3 = new User(inMemoryUserRepository.nextId(), "name", "email@example.com", "password");
    }

    @Test
    @DisplayName("should save user")
    void save() {
        inMemoryUserRepository.save(user1);

        assertThat(inMemoryUserRepository.find(user1.id()))
            .isEqualTo(user1);
    }

    @Test
    @DisplayName("should count users in db")
    void count() {
        assertThat(inMemoryUserRepository.count())
            .isZero();

        inMemoryUserRepository.save(user1);

        assertThat(inMemoryUserRepository.count())
            .isEqualTo(1);

        inMemoryUserRepository.save(user2);
        inMemoryUserRepository.save(user3);

        assertThat(inMemoryUserRepository.count())
            .isEqualTo(3);
    }

    @Test
        //save 3 users and check there are 3 equals users in db
    void findAll() {
        inMemoryUserRepository.save(user1);
        inMemoryUserRepository.save(user2);
        inMemoryUserRepository.save(user3);

        assertThat(inMemoryUserRepository.findAll())
            .hasSize(3)
            .containsOnly(user1, user2, user3);
    }

    @Test
    void find() {
        inMemoryUserRepository.save(user1);
        inMemoryUserRepository.save(user2);

        assertThat(inMemoryUserRepository.find(user1.id()))
            .isEqualTo(user1);
    }

    @Test
    void delete() {
        inMemoryUserRepository.save(user1);
        inMemoryUserRepository.save(user2);

        inMemoryUserRepository.delete(user1);
        var user1Id = user1.id();
        assertThatThrownBy(() -> inMemoryUserRepository.find(user1Id))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("User not found : " + user1.id().value());
        assertThat(inMemoryUserRepository.count())
            .isEqualTo(1); //user2 is still in db
    }

    @Test
    void deleteAll() {
        inMemoryUserRepository.save(user1);
        inMemoryUserRepository.save(user2);
        inMemoryUserRepository.save(user3);

        inMemoryUserRepository.deleteAll();
        assertThat(inMemoryUserRepository.findAll())
            .isEmpty();
    }
}
