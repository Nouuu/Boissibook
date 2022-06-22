package org.esgi.boissibook.features.book.infra.repository;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.kernel.exception.BookConflictException;
import org.esgi.boissibook.features.book.kernel.exception.BookNotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryBookRepositoryTest {

    private InMemoryBookRepository bookRepository;

    Book book1;
    Book book2;
    Book book3;

    @BeforeEach
    void setUp() {
        bookRepository = new InMemoryBookRepository();

        book1 = new Book(
            bookRepository.nextId(),
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
            bookRepository.nextId(),
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
            bookRepository.nextId(),
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

    @Test
    void save() {
        bookRepository.save(book1);

        assertThat(bookRepository.find(book1.id()))
            .isEqualTo(book1);
    }

    @Test
    void saveExistingApiId() {
        bookRepository.save(book1);
        book2.setApiId(book1.apiId());

        assertThatThrownBy(() -> bookRepository.save(book2))
            .isInstanceOf(BookConflictException.class);
    }

    @Test
    void count() {
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

    @Test
    void findAll() {
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        assertThat(bookRepository.findAll())
            .hasSize(3)
            .containsExactlyInAnyOrder(book1, book2, book3);
    }

    @Test
    void find() {
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        assertThat(bookRepository.find(book2.id()))
            .isEqualTo(book2);
    }

    @Test
    void findNotFound() {
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        var bookId = bookRepository.nextId();

        assertThatThrownBy(() -> bookRepository.find(bookId))
            .isInstanceOf(BookNotFoundException.class)
            .hasMessage("Book not found : " + bookId);
    }

    @Test
    void delete() {
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

    @Test
    void deleteAll() {
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        bookRepository.deleteAll();

        assertThat(bookRepository.count())
            .isZero();
    }

    @Test
    void nextId() {
        assertThat(bookRepository.nextId())
            .isNotNull()
            .isInstanceOf(BookId.class);
    }
}
