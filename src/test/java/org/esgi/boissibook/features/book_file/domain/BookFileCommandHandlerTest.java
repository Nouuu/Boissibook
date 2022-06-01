package org.esgi.boissibook.features.book_file.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.esgi.boissibook.features.book.infra.config.SpringBookBeans;
import org.esgi.boissibook.features.book_file.infra.config.SpringBookFileBeans;
import org.esgi.boissibook.infra.SpringEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;


@Import({SpringBookBeans.class, SpringBookFileBeans.class, SpringEventService.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@DataJpaTest
class BookFileCommandHandlerTest {
    @Autowired
    public BookFileCommandHandler bookFileCommandHandler;

    @Autowired
    public BookFileRepository bookFileRepository;

    BookFile bookFile1;
    BookFile bookFile2;
    BookFile bookFile3;


    @BeforeEach
    void setUp() {
        bookFile1 = new BookFile(null, "Filename.pdf", "application/pdf", "book-id", "user-id", 0, new byte[]{});
        bookFile2 = new BookFile(null, "Filename.pdf", "application/pdf", "book-id", "user-id", 0, new byte[]{});
        bookFile3 = new BookFile(null, "Filename.pdf", "application/pdf", "book-id", "user-id", 0, new byte[]{});
    }

    @Test
    void createBookFile() {
        var bookFileId = bookFileCommandHandler.createBookFile(bookFile1);

        assertThat(bookFileRepository.find(bookFile1.id()))
            .isNotNull()
            .isEqualTo(bookFile1.setId(bookFileId));
    }

    @Test
    void deleteBookFile() {
        bookFileRepository.save(bookFile1.setId(bookFileRepository.nextId()));
        bookFileRepository.save(bookFile2.setId(bookFileRepository.nextId()));

        bookFileCommandHandler.deleteBookFile(bookFile1.id());

        assertThat(bookFileRepository.findAll())
            .hasSize(1)
            .containsOnly(bookFile2);
    }
}
