package org.esgi.boissibook.features.book_file.domain;

import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.book.infra.repository.InMemoryBookRepository;
import org.esgi.boissibook.features.book_file.infra.ScrapperBookFileSearch;
import org.esgi.boissibook.features.book_file.infra.ZipFileCompression;
import org.esgi.boissibook.features.book_file.infra.repository.InMemoryBookFileRepository;
import org.esgi.boissibook.kernel.event.VoidEventService;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BookFileCommandHandlerTest {
    private BookFileCommandHandler bookFileCommandHandler;
    private BookFileRepository bookFileRepository;
    private BookRepository bookRepository;
    private BookFileSearch bookFileSearch;

    BookFile bookFile1;
    BookFile bookFile2;
    BookFile bookFile3;


    @BeforeEach
    void setUp() {
        bookFileRepository = new InMemoryBookFileRepository();
        bookRepository = new InMemoryBookRepository();
        bookFileSearch = new ScrapperBookFileSearch("https://www.google.fr/");
        bookFileCommandHandler = new BookFileCommandHandler(bookFileRepository, bookRepository, new VoidEventService(), new ZipFileCompression(), bookFileSearch);

        bookFile1 = new BookFile(null, "Filename.pdf", "application/pdf", BookId.of("book-id"), UserId.of("user-id"), 0, new byte[]{});
        bookFile2 = new BookFile(null, "Filename.pdf", "application/pdf", BookId.of("book-id"), UserId.of("user-id"), 0, new byte[]{});
        bookFile3 = new BookFile(null, "Filename.pdf", "application/pdf", BookId.of("book-id"), UserId.of("user-id"), 0, new byte[]{});
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
