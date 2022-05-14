package org.esgi.boissibook.features.book.domain;

import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import org.esgi.boissibook.features.book.infra.config.SpringBookBeans;
import org.esgi.boissibook.infra.SpringEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@Import({SpringBookBeans.class, SpringEventService.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@DataJpaTest
class BookQueryHandlerTest {
    private Book book1;
    private Book book2;
    private Book book3;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookQueryHandler bookQueryHandler;

    @BeforeEach
    void setUp() {

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
}