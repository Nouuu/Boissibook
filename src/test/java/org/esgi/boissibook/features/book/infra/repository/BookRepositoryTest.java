package org.esgi.boissibook.features.book.infra.repository;

import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.book.kernel.exception.BookConflictException;
import org.esgi.boissibook.features.book.kernel.exception.BookNotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class BookRepositoryTest extends PostgresIntegrationTest {

    @Autowired
    JPABookRepository jpaBookRepository;

    private static final String springDataBookRepositoryKey = "SpringDataBookRepository";

    private static final String inMemoryBookRepositoryKey = "InMemoryBookRepository";

    private HashMap<String, BookRepository> bookRepositories;

    Book book1;
    Book book2;
    Book book3;

    @BeforeEach
    void setUp() {
        SpringDataBookRepository springDataBookRepository = new SpringDataBookRepository(jpaBookRepository);
        InMemoryBookRepository inMemoryBookRepository = new InMemoryBookRepository();

        bookRepositories = new HashMap<>();
        bookRepositories.put(springDataBookRepositoryKey, springDataBookRepository);
        bookRepositories.put(inMemoryBookRepositoryKey, inMemoryBookRepository);

        book1 = new Book(
            springDataBookRepository.nextId(),
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
        book2 = new Book(
            springDataBookRepository.nextId(),
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
        book3 = new Book(
            springDataBookRepository.nextId(),
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
    }

    private static Stream<String> provideRepositories() {
        return Stream.of(
            springDataBookRepositoryKey,
            inMemoryBookRepositoryKey
        );
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void save(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);
        bookRepository.save(book1);

        assertThat(bookRepository.find(book1.id()))
            .isEqualTo(book1);
    }


    @ParameterizedTest
    @MethodSource("provideRepositories")
    void saveExistingApiId(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);

        bookRepository.save(book1);
        book2.setApiId(book1.apiId());

        assertThatThrownBy(() -> bookRepository.save(book2))
            .isInstanceOf(BookConflictException.class);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void count(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);

        assertThat(bookRepository.count())
            .isZero();

        bookRepository.save(book1);

        assertThat(bookRepository.count())
            .isEqualTo(1);

        bookRepository.save(book2);
        bookRepository.save(book3);

        assertThat(bookRepository.count())
            .isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findAll(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        assertThat(bookRepository.findAll())
            .hasSize(3)
            .containsExactlyInAnyOrder(book1, book2, book3);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void find(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        assertThat(bookRepository.find(book2.id()))
            .isEqualTo(book2);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findNotFound(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        var bookId = bookRepository.nextId();

        assertThatThrownBy(() -> bookRepository.find(bookId))
            .isInstanceOf(BookNotFoundException.class)
            .hasMessage("Book not found : " + bookId);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void delete(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);

        bookRepository.save(book1);
        bookRepository.save(book2);

        bookRepository.delete(book1);
        var bookId = book1.id();

        assertThatThrownBy(() -> bookRepository.find(bookId))
            .isInstanceOf(BookNotFoundException.class)
            .hasMessage("Book not found : " + bookId);

        assertThat(bookRepository.count())
            .isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void deleteAll(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        bookRepository.deleteAll();

        assertThat(bookRepository.count())
            .isZero();
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void nextId(String bookRepositoryKey) {
        BookRepository bookRepository = bookRepositories.get(bookRepositoryKey);

        assertThat(bookRepository.nextId())
            .isNotNull()
            .isInstanceOf(BookId.class);
    }
}
