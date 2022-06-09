package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@DataJpaTest
class SpringDataUserRepositoryTest {

    @Autowired
    JPAUserRepository userRepository;

    private SpringDataUserRepository springDataUserRepository;

    UserEntity userEntity1;
    UserEntity userEntity2;
    UserEntity userEntity3;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() {
        springDataUserRepository = new SpringDataUserRepository(userRepository);
        user1 = new User(springDataUserRepository.nextId(), "name", "email@example.com", "password");
        user2 = new User(springDataUserRepository.nextId(), "name", "email@example.com", "password");
        user3 = new User(springDataUserRepository.nextId(), "name", "email@example.com", "password");
        userEntity1 = UserEntityMapper.toUserEntity(user1);
        userEntity2 = UserEntityMapper.toUserEntity(user2);
        userEntity3 = UserEntityMapper.toUserEntity(user3);
    }

    @Test
    @DisplayName("should save user")
    void save() {
        springDataUserRepository.save(user1);

        assertThat(userRepository.findById(user1.id().value()))
            .isPresent()
            .get()
            .isEqualTo(userEntity1);
    }

    @Test
    @DisplayName("should count users in db")
    void count() {
        assertThat(userRepository.count())
            .isZero();

        springDataUserRepository.save(user1);

        assertThat(userRepository.count())
            .isEqualTo(1);

        springDataUserRepository.save(user2);
        springDataUserRepository.save(user3);

        assertThat(userRepository.count())
            .isEqualTo(3);
    }

    @Test
        //save 3 users and check there are 3 equals users in db
    void findAll() {
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);
        userRepository.save(userEntity3);

        assertThat(springDataUserRepository.findAll())
            .hasSize(3)
            .containsOnly(user1, user2, user3);
    }

    @Test
    void find() {
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);

        assertThat(springDataUserRepository.find(user1.id()))
            .isEqualTo(user1);
    }

    @Test
    void delete() {
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);

        springDataUserRepository.delete(user1);
        assertThat(userRepository.findById(user1.id().value()))
            .isNotPresent();
        assertThat(userRepository.count())
            .isEqualTo(1); //user2 is still in db
    }

    @Test
    void deleteAll() {
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);
        userRepository.save(userEntity3);

        springDataUserRepository.deleteAll();
        assertThat(userRepository.findAll())
            .isEmpty();
    }
}
