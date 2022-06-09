package org.esgi.boissibook.features.user.domain;

import org.esgi.boissibook.features.user.infra.config.SpringUserBeans;
import org.esgi.boissibook.infra.SpringEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@Import({SpringUserBeans.class, SpringEventService.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@DataJpaTest
class UserCommandHandlerTest {

    @Autowired
    public UserCommandHandler userCommandHandler;

    @Autowired
    public UserRepository userRepository;

    User user1;
    User user2;
    User user3;


    @BeforeEach
    void setUp() {
        user1 = new User(null, "name", "email@example.com", "password");
        user2 = new User(null, "name", "email@example.com", "password");
        user3 = new User(null, "name", "email@example.com", "password");
    }

    @Test
    void createUser() {
        var userId = userCommandHandler.createUser(user1);

        assertThat(userId).isNotNull();
        assertThat(userRepository.find(userId))
            .isNotNull()
            .isEqualTo(user1.setId(userId));
    }

    @Test
    void updateUser() {
        userRepository.save(user1.setId(userRepository.nextId()));

        user1.setName("newName")
            .setPassword(null);

        userCommandHandler.updateUser(user1);
        assertThat(userRepository.find(user1.id()))
            .isEqualTo(user1.setPassword("password"));
    }

    @Test
    void deleteUser() {
        userRepository.save(user1.setId(userRepository.nextId()));
        userRepository.save(user2.setId(userRepository.nextId()));

        userCommandHandler.deleteUser(user1.id());

        assertThat(userRepository.findAll())
            .hasSize(1)
            .containsOnly(user2);
    }

    @Test
    void deleteAllUsers() {
        userRepository.save(user1.setId(userRepository.nextId()));
        userRepository.save(user2.setId(userRepository.nextId()));
        userRepository.save(user3.setId(userRepository.nextId()));

        userCommandHandler.deleteAllUsers();

        assertThat(userRepository.findAll())
            .isEmpty();
    }
}
