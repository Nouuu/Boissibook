package org.esgi.boissibook.features.book_file.domain;

import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import org.esgi.boissibook.features.book_file.infra.config.SpringBookFileBeans;
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
class BookFileQueryHandlerTest {
    @Autowired
    public BookFileRepository bookFileRepository;
    BookFile book1;
    BookFile book3;
    BookFile book2;
    String bookId1;
    String bookId2;
    @Autowired
    private BookFileQueryHandler bookFileQueryHandler;

    @BeforeEach
    void setUp() {
        bookId1 = UUID.randomUUID().toString();
        bookId2 = UUID.randomUUID().toString();

        book1 = new BookFile(null, "Filename.pdf", "application/pdf", bookId1, "user-id", 0, new byte[]{});
        book2 = new BookFile(null, "Filename.pdf", "application/pdf", bookId2, "user-id", 0, new byte[]{});
        book3 = new BookFile(null, "Filename.pdf", "application/pdf", bookId1, "user-id", 0, new byte[]{});

        bookFileRepository.save(book1.setId(bookFileRepository.nextId()));
        bookFileRepository.save(book2.setId(bookFileRepository.nextId()));
        bookFileRepository.save(book3.setId(bookFileRepository.nextId()));
    }

    @Test
    void getBookFiles() {
        assertThat(bookFileQueryHandler.getBookFiles(bookId1))
            .hasSize(2)
            .containsOnly(book1, book3);
    }

    @Test
    void getBookFileById() {
        bookFileQueryHandler.getBookFileById(book1.id());

        BookFile bookFile = bookFileRepository.find(book1.id());
        assertThat(bookFile.downloadCount())
            .isEqualTo(1);
    }

    @Test
    void getBookFilesCountByBookId() {
        assertThat(bookFileQueryHandler.countBookFiles(bookId1))
            .isEqualTo(2);
    }
}
