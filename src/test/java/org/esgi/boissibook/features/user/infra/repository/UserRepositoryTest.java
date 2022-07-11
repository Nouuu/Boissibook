package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.domain.UserRepository;
import org.esgi.boissibook.features.user.kernel.exception.UserNotFoundException;
import org.esgi.boissibook.kernel.repository.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest extends PostgresIntegrationTest {

    @Autowired
    JPAUserRepository jpaUserRepository;

    private final static String springDataUserRepositoryKey = "SpringDataUserRepository";

    private final static String inMemoryUserRepositoryKey = "InMemoryUserRepository";

    private HashMap<String, UserRepository> userRepositories;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() {
        SpringDataUserRepository userRepository = new SpringDataUserRepository(jpaUserRepository);
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

        userRepositories = new HashMap<>();
        userRepositories.put(springDataUserRepositoryKey, userRepository);
        userRepositories.put(inMemoryUserRepositoryKey, inMemoryUserRepository);


        user1 = new User(UserId.nextId(), "name1", "email1@example.com", "password");
        user2 = new User(UserId.nextId(), "name2", "email2@example.com", "password");
        user3 = new User(UserId.nextId(), "name3", "email3@example.com", "password");
    }

    private static Stream<String> provideRepositories() {
        return Stream.of(
            springDataUserRepositoryKey,
            inMemoryUserRepositoryKey
        );
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void save(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        userRepository.save(user1);

        assertThat(userRepository.find(user1.id()))
            .isEqualTo(user1);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void count(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

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

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findAll(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        assertThat(userRepository.findAll())
            .hasSize(3)
            .containsOnly(user1, user2, user3);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void find(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        userRepository.save(user1);
        userRepository.save(user2);

        assertThat(userRepository.find(user1.id()))
            .isEqualTo(user1);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findNotFound(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        var userId = userRepository.nextId();
        assertThatThrownBy(() -> userRepository.find(userId))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("User not found : " + userId.value());
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void delete(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

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

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void deleteAll(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        userRepository.deleteAll();
        assertThat(userRepository.findAll())
            .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findByEmail(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        assertThat(userRepository.findByEmail(user2.email()))
            .isEqualTo(user2);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findByEmailNotFound(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        assertThatThrownBy(() -> userRepository.findByEmail("notexisting@email.com"))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessage("User not found : notexisting@email.com");
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void nextId(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        assertThat(userRepository.nextId())
            .isNotNull()
            .isInstanceOf(UserId.class);
    }
}
