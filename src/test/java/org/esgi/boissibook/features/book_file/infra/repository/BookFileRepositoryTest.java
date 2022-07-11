package org.esgi.boissibook.features.book_file.infra.repository;

import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.esgi.boissibook.features.book_file.domain.BookFileRepository;
import org.esgi.boissibook.features.book_file.kernel.exception.BookFileNotFoundException;
import org.esgi.boissibook.kernel.repository.BookFileId;
import org.esgi.boissibook.kernel.repository.BookId;
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
class BookFileRepositoryTest extends PostgresIntegrationTest {

    @Autowired
    JPABookFileRepository jpaBookFileRepository;

    private final static String springDataBookFileRepositoryKey = "SpringDataBookFileRepository";

    private final static String inMemoryBookFileRepositoryKey = "InMemoryBookFileRepository";

    private HashMap<String, BookFileRepository> bookFileRepositories;

    BookFile bookFile1;
    BookFile bookFile2;
    BookFile bookFile3;

    @BeforeEach
    void setUp() {
        SpringDataBookFileRepository springDataBookFileRepository = new SpringDataBookFileRepository(jpaBookFileRepository);
        InMemoryBookFileRepository inMemoryBookFileRepository = new InMemoryBookFileRepository();

        bookFileRepositories = new HashMap<>();
        bookFileRepositories.put(springDataBookFileRepositoryKey, springDataBookFileRepository);
        bookFileRepositories.put(inMemoryBookFileRepositoryKey, inMemoryBookFileRepository);

        bookFile1 = new BookFile(
            springDataBookFileRepository.nextId(),
            "Book 1",
            "application/epub",
            BookId.nextId(),
            UserId.nextId(),
            2,
            null
        );
        bookFile2 = new BookFile(
            springDataBookFileRepository.nextId(),
            "Book 2",
            "application/epub",
            BookId.nextId(),
            UserId.nextId(),
            6,
            null
        );
        bookFile3 = new BookFile(
            springDataBookFileRepository.nextId(),
            "Book 3",
            "application/pdf",
            BookId.nextId(),
            UserId.nextId(),
            10,
            null
        );
    }

    private static Stream<String> provideRepositories() {
        return Stream.of(
            springDataBookFileRepositoryKey,
            inMemoryBookFileRepositoryKey
        );
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void save(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        bookFileRepository.save(bookFile1);

        assertThat(bookFileRepository.find(bookFile1.id()))
            .isEqualTo(bookFile1);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void count(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        assertThat(bookFileRepository.count())
            .isZero();

        bookFileRepository.save(bookFile1);

        assertThat(bookFileRepository.count())
            .isEqualTo(1);

        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        assertThat(bookFileRepository.count())
            .isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findAll(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        assertThat(bookFileRepository.findAll())
            .hasSize(3)
            .containsOnly(bookFile1, bookFile2, bookFile3);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void find(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        assertThat(bookFileRepository.find(bookFile2.id()))
            .isEqualTo(bookFile2);
    }

    //test find not found
    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findNotFound(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        var bookFileId = BookFileId.nextId();

        assertThatThrownBy(() -> bookFileRepository.find(bookFileId))
            .isInstanceOf(BookFileNotFoundException.class)
            .hasMessage("Book file not found : " + bookFileId);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void delete(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);

        bookFileRepository.delete(bookFile1);
        var bookFileId = bookFile1.id();

        assertThatThrownBy(() -> bookFileRepository.find(bookFileId))
            .isInstanceOf(BookFileNotFoundException.class)
            .hasMessage("Book file not found : " + bookFileId);

        assertThat(bookFileRepository.count())
            .isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void deleteAll(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        bookFileRepository.deleteAll();

        assertThat(bookFileRepository.count())
            .isZero();
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void nextId(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        assertThat(bookFileRepository.nextId())
            .isNotNull()
            .isInstanceOf(BookFileId.class);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findByBookId(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        bookFile2.setBookId(bookFile1.bookId());
        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        assertThat(bookFileRepository.findByBookId(bookFile2.bookId()))
            .hasSize(2)
            .containsOnly(bookFile1, bookFile2);

        assertThat(bookFileRepository.findByBookId(BookId.nextId()))
            .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void countAllByBookId(String bookFileRepositoryKey) {
        BookFileRepository bookFileRepository = bookFileRepositories.get(bookFileRepositoryKey);

        bookFile2.setBookId(bookFile1.bookId());
        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        assertThat(bookFileRepository.countAllByBookId(bookFile2.bookId()))
            .isEqualTo(2);

        assertThat(bookFileRepository.countAllByBookId(BookId.nextId()))
            .isZero();
    }
}
