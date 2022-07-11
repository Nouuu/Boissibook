package org.esgi.boissibook.features.user.infra.web;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.user.infra.web.request.CreateUserRequest;
import org.esgi.boissibook.features.user.infra.web.response.UserResponse;
import org.esgi.boissibook.infra.web.HandledExceptionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class UserCommandsAPITest extends PostgresIntegrationTest {

    final CreateUserRequest validUser1 = new CreateUserRequest(
        "user1",
        "user1@mail.com",
        "Password123");

    final CreateUserRequest invalidUser1 = new CreateUserRequest(
        "user1",
        "user",
        "password123");

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void createUser() {

        var getUserUri = given()
            .contentType(JSON)
            .body(validUser1)
            .when()
            .post("/users")
            .then()
            .statusCode(201)
            .extract()
            .header("location");

        var user = given()
            .baseUri(getUserUri)
            .when()
            .get()
            .then().statusCode(200)
            .extract()
            .body().as(UserResponse.class);

        assertThat(user.userId()).isNotNull();
        assertThat(user.email()).isEqualTo(validUser1.email());
        assertThat(user.name()).isEqualTo(validUser1.name());
    }

    @Test
    void createUserInvalid() {

        var response = given()
            .contentType(JSON)
            .body(invalidUser1)
            .when()
            .post("/users")
            .then()
            .statusCode(400)
            .extract()
            .body().as(HandledExceptionResponse.class);

        assertThat(response.message())
            .contains("email: Invalid email")
            .contains("password: Invalid Password");
    }

}

