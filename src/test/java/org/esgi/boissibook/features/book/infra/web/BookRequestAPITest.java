package org.esgi.boissibook.features.book.infra.web;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.infra.BookMapper;
import org.esgi.boissibook.features.book.infra.repository.JPABookRepository;
import org.esgi.boissibook.features.book.infra.web.response.BookResponse;
import org.esgi.boissibook.kernel.repository.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class BookRequestAPITest extends PostgresIntegrationTest {

    final Book book1 = new Book(
        BookId.nextId(),
        "123456789",
        "Book 1",
        List.of("Author 1", "Author 2"),
        "publisher 1",
        "2022-02-02",
        "description 1",
        "1234567890123",
        "fr",
        "imgUrl",
        203
    );
    final Book book2 = new Book(
        BookId.nextId(),
        "1234789",
        "Book 2",
        List.of("Author 1", "Author 2"),
        "publisher 1",
        "2022-02-02",
        "description 1",
        "1234567890123",
        "fr",
        "imgUrl",
        203
    );
    final Book book3 = new Book(
        BookId.nextId(),
        "12345678",
        "Book 3",
        List.of("Author 1", "Author 2"),
        "publisher 1",
        "2022-02-02",
        "description 1",
        "1234567890123",
        "fr",
        "imgUrl",
        203
    );

    @LocalServerPort
    int port;

    @Autowired
    JPABookRepository jpaBookRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void getAllBooksEmpty() {
        var bookResources = when()
            .get("/books")
            .then()
            .statusCode(200)
            .extract()
            .body().jsonPath().getList("books", BookResponse.class);

        assertThat(bookResources).isEmpty();
    }

    @Test
    void getAllBooks() {
        jpaBookRepository.save(BookMapper.mapBookToBookEntity(book1));
        jpaBookRepository.save(BookMapper.mapBookToBookEntity(book2));
        jpaBookRepository.save(BookMapper.mapBookToBookEntity(book3));

        var bookResources = when()
            .get("/books")
            .then()
            .statusCode(200)
            .extract()
            .body().jsonPath().getList("books", BookResponse.class);

        assertThat(bookResources).hasSize(3)
            .containsOnly(
                BookMapper.mapBookToBookResponse(book1),
                BookMapper.mapBookToBookResponse(book2),
                BookMapper.mapBookToBookResponse(book3)
            );
    }

    @Test
    void getBookById() {
        jpaBookRepository.save(BookMapper.mapBookToBookEntity(book1));
        jpaBookRepository.save(BookMapper.mapBookToBookEntity(book2));
        jpaBookRepository.save(BookMapper.mapBookToBookEntity(book3));

        var bookResource = when()
            .get("/books/{id}", book1.id().value())
            .then()
            .statusCode(200)
            .extract()
            .body().as(BookResponse.class);

        assertThat(bookResource).isEqualTo(BookMapper.mapBookToBookResponse(book1));
    }

    @Test
    void getBookByIdNotFound() {
        when()
            .get("/books/{id}", book1.id().value())
            .then()
            .statusCode(404);
    }
}

