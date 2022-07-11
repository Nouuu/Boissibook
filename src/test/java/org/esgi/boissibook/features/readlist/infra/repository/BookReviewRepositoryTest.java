package org.esgi.boissibook.features.readlist.infra.repository;

import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.BookReviewRepository;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;
import org.esgi.boissibook.features.readlist.kernel.exception.BookReviewNotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
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
class BookReviewRepositoryTest extends PostgresIntegrationTest {

    @Autowired
    JPABookReviewRepository jpaBookReviewRepository;

    private final static String springDataBookReviewRepositoryKey = "SpringDataBookReviewRepository";

    private final static String inMemoryBookReviewRepositoryKey = "InMemoryBookReviewRepository";

    private HashMap<String, BookReviewRepository> bookReviewRepositories;

    BookReview bookReview1;
    BookReview bookReview2;
    BookReview bookReview3;

    @BeforeEach
    void setUp() {
        SpringDataBookReviewRepository bookReviewRepository = new SpringDataBookReviewRepository(jpaBookReviewRepository);
        InMemoryBookReviewRepository inMemoryBookReviewRepository = new InMemoryBookReviewRepository();

        bookReviewRepositories = new HashMap<>();
        bookReviewRepositories.put(springDataBookReviewRepositoryKey, bookReviewRepository);
        bookReviewRepositories.put(inMemoryBookReviewRepositoryKey, inMemoryBookReviewRepository);

        bookReview1 = new BookReview(
            bookReviewRepository.nextId(),
            BookId.nextId(),
            UserId.nextId(),
            Visibility.PUBLIC,
            ReadingStatus.COMPLETED,
            32,
            5,
            "review1"
        );
        bookReview2 = new BookReview(
            bookReviewRepository.nextId(),
            BookId.nextId(),
            UserId.nextId(),
            Visibility.PUBLIC,
            ReadingStatus.COMPLETED,
            320,
            2,
            "review2"
        );
        bookReview3 = new BookReview(
            bookReviewRepository.nextId(),
            BookId.nextId(),
            UserId.nextId(),
            Visibility.PUBLIC,
            ReadingStatus.COMPLETED,
            68,
            3,
            "review3"
        );
    }

    private static Stream<String> provideRepositories() {
        return Stream.of(
            springDataBookReviewRepositoryKey,
            inMemoryBookReviewRepositoryKey
        );
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void nextId(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        assertThat(bookReviewRepository.nextId())
            .isNotNull()
            .isInstanceOf(BookReviewId.class);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void save(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);

        assertThat(bookReviewRepository.find(bookReview1.id()))
            .isEqualTo(bookReview1);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void count(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        assertThat(bookReviewRepository.count())
            .isZero();

        bookReviewRepository.save(bookReview1);

        assertThat(bookReviewRepository.count())
            .isEqualTo(1);

        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.count())
            .isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findAll(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.findAll())
            .hasSize(3)
            .containsOnly(bookReview1, bookReview2, bookReview3);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void find(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.find(bookReview2.id()))
            .isEqualTo(bookReview2);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findNotFound(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        var bookReviewId = BookReviewId.nextId();

        assertThatThrownBy(() -> bookReviewRepository.find(bookReviewId))
            .isInstanceOf(BookReviewNotFoundException.class)
            .hasMessage("Review not found : " + bookReviewId);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void delete(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);

        bookReviewRepository.delete(bookReview1);
        var bookReviewId = bookReview1.id();

        assertThatThrownBy(() -> bookReviewRepository.find(bookReviewId))
            .isInstanceOf(BookReviewNotFoundException.class)
            .hasMessage("Review not found : " + bookReviewId);

        assertThat(bookReviewRepository.count())
            .isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void deleteAll(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        bookReviewRepository.deleteAll();

        assertThat(bookReviewRepository.count())
            .isZero();
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findByBookIdAndUserId(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.findByBookIdAndUserId(bookReview2.getBookId(), bookReview2.getUserId()))
            .isEqualTo(bookReview2);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findByBookIdAndUserIdNotFound(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        var bookId = bookReview1.getBookId();
        var userId = bookReview2.getUserId();
        assertThatThrownBy(() -> bookReviewRepository.findByBookIdAndUserId(bookId, userId))
            .isInstanceOf(BookReviewNotFoundException.class)
            .hasMessage("Review not found : " + bookId + ", " + userId);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findByUserId(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReview2.setUserId(bookReview1.getUserId());
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.findByUserId(bookReview1.getUserId()))
            .hasSize(2)
            .containsOnly(bookReview1, bookReview2);
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void findByUserIdEmpty(String bookRepositoryKey) {
        BookReviewRepository bookReviewRepository = bookReviewRepositories.get(bookRepositoryKey);

        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.findByUserId(UserId.nextId()))
            .isEmpty();
    }
}
