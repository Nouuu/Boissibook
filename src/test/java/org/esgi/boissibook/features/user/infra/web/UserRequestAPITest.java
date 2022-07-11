package org.esgi.boissibook.features.user.infra.web;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.infra.UserMapper;
import org.esgi.boissibook.features.user.infra.repository.JPAUserRepository;
import org.esgi.boissibook.features.user.infra.web.response.UserResponse;
import org.esgi.boissibook.kernel.repository.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class UserRequestAPITest extends PostgresIntegrationTest {

    final User validUser1 = new User(
        UserId.nextId(),
        "user1",
        "user1@mail.com",
        "password123");

    final User validUser2 = new User(
        UserId.nextId(),
        "user2",
        "user2@mail.com",
        "password123");


    @LocalServerPort
    int port;

    @Autowired
    JPAUserRepository jpaUserRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void getAllUsers() {
        jpaUserRepository.save(UserMapper.toUserEntity(validUser1));
        jpaUserRepository.save(UserMapper.toUserEntity(validUser2));


        var userResources = when()
            .get("/users")
            .then()
            .statusCode(200)
            .extract()
            .body().jsonPath().getList(".", UserResponse.class);

        assertThat(userResources).hasSize(2)
            .containsExactly(
                UserMapper.toUserResponse(validUser1),
                UserMapper.toUserResponse(validUser2)
            );
    }

    @Test
    void getAllUsersEmpty() {

        var userResources = when()
            .get("/users")
            .then()
            .statusCode(200)
            .extract()
            .body().jsonPath().getList(".", UserResponse.class);

        assertThat(userResources).isEmpty();
    }

    @Test
    void getUserById() {
        jpaUserRepository.save(UserMapper.toUserEntity(validUser1));

        var userResource = when()
            .get("/users/" + validUser1.id().value())
            .then()
            .statusCode(200)
            .extract()
            .body().jsonPath().getObject(".", UserResponse.class);

        assertThat(userResource).isEqualTo(UserMapper.toUserResponse(validUser1));
    }

    @Test
    void getUserByIdNotFound() {
        when()
            .get("/users/" + validUser1.id().value())
            .then()
            .statusCode(404);
    }
}

