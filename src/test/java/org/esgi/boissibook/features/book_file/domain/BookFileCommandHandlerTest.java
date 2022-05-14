package org.esgi.boissibook.features.book_file.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.esgi.boissibook.features.book_file.infra.config.SpringBookFileBeans;
import org.esgi.boissibook.features.user.infra.config.SpringUserBeans;
import org.esgi.boissibook.infra.SpringEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;


@Import({SpringBookFileBeans.class, SpringEventService.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@DataJpaTest
class BookFileCommandHandlerTest {
    @Autowired
    public BookFileCommandHandler bookFileCommandHandler;

    @Autowired
    public BookFileRepository bookFileRepository;

    BookFile book1;
    BookFile book3;
    BookFile book2;


    @BeforeEach
    void setUp() {
        book1 = new BookFile(null, "Filename.pdf", "application/pdf", "book-id", "user-id", 0, new byte[]{});
        book2 = new BookFile(null, "Filename.pdf", "application/pdf", "book-id", "user-id", 0, new byte[]{});
        book3 = new BookFile(null, "Filename.pdf", "application/pdf", "book-id", "user-id", 0, new byte[]{});
    }

    @Test
    void createBook() {
        var bookFileId = bookFileCommandHandler.createBookFile(book1);

        assertThat(bookFileRepository.find(book1.id()))
            .isNotNull()
            .isEqualTo(book1.setId(bookFileId));
    }

    @Test
    void deleteBook() {
        bookFileRepository.save(book1.setId(bookFileRepository.nextId()));
        bookFileRepository.save(book2.setId(bookFileRepository.nextId()));

        bookFileCommandHandler.deleteBookFile(book1.id());

        assertThat(bookFileRepository.findAll())
            .hasSize(1)
            .containsOnly(book2);
    }
}