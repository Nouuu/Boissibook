package org.esgi.boissibook.features.book.domain;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.esgi.boissibook.features.book.infra.config.SpringBookBeans;
import org.esgi.boissibook.features.book.kernel.exception.BookNotFoundException;
import org.esgi.boissibook.infra.SpringEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@Import({SpringBookBeans.class, SpringEventService.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@DataJpaTest
class BookCommandHandlerTest {
    @Autowired
    private BookCommandHandler bookCommandHandler;

    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;


    @BeforeEach
    void setUp() {
        book1 = new Book(null, "1", "title1", List.of("author1"), "publisher1", "2001", "description1", "isbn1", "fr1", "imgUrl1", 101);
        book2 = new Book(null, "2", "title2", List.of("author2"), "publisher2", "2002", "description2", "isbn2", "fr2", "imgUrl2", 202);
    }

    @Test
    @DisplayName("Should add a book")
    void addBook() {
        String id = bookCommandHandler.addBook(book1);
        book1.setId(id);

        assertThat(bookRepository.find(id))
            .isNotNull()
            .isEqualTo(book1);
    }

    @Test
    @DisplayName("Should delete a book by it's id")
    void deleteBook() {
        bookRepository.save(book1.setId(bookRepository.nextId()));
        bookRepository.save(book2.setId(bookRepository.nextId()));

        bookCommandHandler.deleteBook(book1.id());

        assertThat(bookRepository.findAll())
            .hasSize(1)
            .containsOnly(book2);
    }

    @Test
    @DisplayName("Should throw book not found exception when deleting a book that doesn't exist")
    void throwBookNotFoundException() {
        String id = bookRepository.nextId();
        bookRepository.save(book1.setId(bookRepository.nextId()));
        bookRepository.save(book2.setId(bookRepository.nextId()));

        assertThatThrownBy(() -> bookCommandHandler.deleteBook(id))
            .isInstanceOf(BookNotFoundException.class);
    }

   /* @Test
    @DisplayName("Should throw book conflict exception when adding a book already added")
    void throwBookConflictException() {
        bookRepository.save(book1.setId(bookRepository.nextId()));
        book2.setApiId(book1.apiId());
        bookRepository.save(book2.setId(bookRepository.nextId()));
        bookRepository.findAll(); // H2 Throws here ????????

        assertThatThrownBy(() -> bookCommandHandler.addBook(book2))
            .isInstanceOf(BookConflictException.class);
    }
    */
}