package org.esgi.boissibook.features.book_file.infra.repository;

import org.esgi.boissibook.PostgresIntegrationTest;
import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.esgi.boissibook.features.book_file.kernel.exception.BookFileNotFoundException;
import org.esgi.boissibook.kernel.repository.BookFileId;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class SpringDataBookFileRepositoryTest extends PostgresIntegrationTest {

    @Autowired
    JPABookFileRepository jpaBookFileRepository;

    private SpringDataBookFileRepository bookFileRepository;

    BookFile bookFile1;
    BookFile bookFile2;
    BookFile bookFile3;

    @BeforeEach
    void setUp() {
        bookFileRepository = new SpringDataBookFileRepository(jpaBookFileRepository);

        bookFile1 = new BookFile(
            bookFileRepository.nextId(),
            "Book 1",
            "application/epub",
            BookId.nextId(),
            UserId.nextId(),
            2,
            null
        );
        bookFile2 = new BookFile(
            bookFileRepository.nextId(),
            "Book 2",
            "application/epub",
            BookId.nextId(),
            UserId.nextId(),
            6,
            null
        );
        bookFile3 = new BookFile(
            bookFileRepository.nextId(),
            "Book 3",
            "application/pdf",
            BookId.nextId(),
            UserId.nextId(),
            10,
            null
        );
    }

    @Test
    void save() {
        bookFileRepository.save(bookFile1);

        assertThat(bookFileRepository.find(bookFile1.id()))
            .isEqualTo(bookFile1);
    }

    @Test
    void count() {
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

    @Test
    void findAll() {
        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        assertThat(bookFileRepository.findAll())
            .hasSize(3)
            .containsOnly(bookFile1, bookFile2, bookFile3);
    }

    @Test
    void find() {
        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        assertThat(bookFileRepository.find(bookFile2.id()))
            .isEqualTo(bookFile2);
    }

    //test find not found
    @Test
    void findNotFound() {
        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        var bookFileId = BookFileId.nextId();

        assertThatThrownBy(() -> bookFileRepository.find(bookFileId))
            .isInstanceOf(BookFileNotFoundException.class)
            .hasMessage("Book file not found : " + bookFileId);
    }

    @Test
    void delete() {
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

    @Test
    void deleteAll() {
        bookFileRepository.save(bookFile1);
        bookFileRepository.save(bookFile2);
        bookFileRepository.save(bookFile3);

        bookFileRepository.deleteAll();

        assertThat(bookFileRepository.count())
            .isZero();
    }

    @Test
    void nextId() {
        assertThat(bookFileRepository.nextId())
            .isNotNull()
            .isInstanceOf(BookFileId.class);
    }

    @Test
    void findByBookId() {
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

    @Test
    void countAllByBookId() {
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
