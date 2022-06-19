package org.esgi.boissibook.features.book_file.domain;

import org.esgi.boissibook.features.book_file.infra.ZipFileCompression;
import org.esgi.boissibook.features.book_file.infra.repository.InMemoryBookFileRepository;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookFileQueryHandlerTest {
    private BookFileRepository bookFileRepository;
    private BookFileQueryHandler bookFileQueryHandler;

    BookFile book1;
    BookFile book2;
    BookFile book3;
    BookId bookId1;
    BookId bookId2;

    @BeforeEach
    void setUp() {
        bookFileRepository = new InMemoryBookFileRepository();
        bookFileQueryHandler = new BookFileQueryHandler(bookFileRepository, new ZipFileCompression());

        bookId1 = BookId.nextId();
        bookId2 = BookId.nextId();

        book1 = new BookFile(null, "Filename.pdf", "application/pdf", bookId1, UserId.of("user-id"), 0, new byte[]{});
        book2 = new BookFile(null, "Filename.pdf", "application/pdf", bookId2, UserId.of("user-id"), 0, new byte[]{});
        book3 = new BookFile(null, "Filename.pdf", "application/pdf", bookId1, UserId.of("user-id"), 0, new byte[]{});

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
    @DisplayName("should return the book file increment the book file download count")
    void getBookFileById() {
        assertThat(bookFileQueryHandler.getBookFileById(book1.id()))
            .isNotNull()
            .isEqualTo(book1);

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
