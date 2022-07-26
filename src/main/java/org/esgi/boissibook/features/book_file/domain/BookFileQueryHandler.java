package org.esgi.boissibook.features.book_file.domain;

import org.esgi.boissibook.features.book_file.domain.event.BookFileDownloadedEvent;
import org.esgi.boissibook.kernel.event.EventService;
import org.esgi.boissibook.kernel.repository.BookFileId;
import org.esgi.boissibook.kernel.repository.BookId;

import java.util.List;

public class BookFileQueryHandler {
    private final EventService eventService;
    private final BookFileRepository bookFileRepository;
    private final FileCompression fileCompression;

    public BookFileQueryHandler(BookFileRepository bookFileRepository, FileCompression fileCompression, EventService eventService) {
        this.bookFileRepository = bookFileRepository;
        this.fileCompression = fileCompression;
        this.eventService = eventService;
    }

    public List<BookFile> getBookFiles(BookId bookId) {
        return bookFileRepository.findByBookId(bookId);
    }

    public BookFile getBookFileById(BookFileId id) {
        BookFile bookFile = bookFileRepository.find(id);
        bookFile.setDownloadCount(bookFile.downloadCount() + 1);
        bookFileRepository.save(bookFile);
        bookFile.setContent(fileCompression.decompress(bookFile.name(), bookFile.content()));
        if (bookFile.userId() != null) {
            eventService.publish(BookFileDownloadedEvent.of(bookFile));
        }
        return bookFile;
    }

    public long countBookFiles(BookId bookId) {
        return bookFileRepository.countAllByBookId(bookId);
    }
}
