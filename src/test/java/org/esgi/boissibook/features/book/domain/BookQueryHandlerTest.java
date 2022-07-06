package org.esgi.boissibook.features.book.domain;

import org.esgi.boissibook.features.book.infra.repository.InMemoryBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookQueryHandlerTest {
    private Book book1;
    private Book book2;
    private Book book3;
    private BookRepository bookRepository;
    private BookQueryHandler bookQueryHandler;

    @BeforeEach
    void setUp() {
        bookRepository = new InMemoryBookRepository();
        bookQueryHandler = new BookQueryHandler(bookRepository);

        book1 = new Book(null, "1", "title1", List.of("author1"), "publisher1", "2001", "description1", "isbn1", "fr1", "imgUrl1", 101);
        book2 = new Book(null, "2", "title2", List.of("author2"), "publisher2", "2002", "description2", "isbn2", "fr2", "imgUrl2", 202);
        book3 = new Book(null, "3", "title3", List.of("author3"), "publisher3", "2003", "description3", "isbn3", "fr3", "imgUrl3", 303);

        bookRepository.save(book1.setId(bookRepository.nextId()));
        bookRepository.save(book2.setId(bookRepository.nextId()));
        bookRepository.save(book3.setId(bookRepository.nextId()));
    }

    @Test
    void getBooks() {
        assertThat(bookQueryHandler.getBooks())
            .hasSize(3)
            .containsOnly(book1, book2, book3);
    }

    @Test
    void getBook() {
        Book book = bookQueryHandler.getBook(book1.id());
        assertThat(book)
            .isEqualTo(book1);
    }

    @Test
    void getBookByApiId() {
        Book book = bookQueryHandler.getBookByApiId(book1.apiId());
        assertThat(book)
            .isEqualTo(book1);
    }
}
