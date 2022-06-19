package org.esgi.boissibook.features.book_file.domain;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.book_file.domain.event.BookFileAddedEvent;
import org.esgi.boissibook.features.book_file.domain.event.BookFileDeletedEvent;
import org.esgi.boissibook.kernel.event.EventService;
import org.esgi.boissibook.kernel.repository.BookFileId;
import org.esgi.boissibook.kernel.repository.BookId;

public final class BookFileCommandHandler {
    private final BookFileRepository bookFileRepository;
    private final BookRepository bookRepository;
    private final EventService eventService;
    private final FileCompression fileCompression;
    private final BookFileSearch bookFileSearch;

    public BookFileCommandHandler(BookFileRepository bookFileRepository, BookRepository bookRepository, EventService eventService, FileCompression fileCompression, BookFileSearch bookFileSearch) {
        this.bookFileRepository = bookFileRepository;
        this.bookRepository = bookRepository;
        this.eventService = eventService;
        this.fileCompression = fileCompression;
        this.bookFileSearch = bookFileSearch;
    }

    public BookFileId createBookFile(BookFile bookFile) {
        BookFileId bookFileId = bookFileRepository.nextId();
        bookFile.setId(bookFileId);
        bookFile.setContent(fileCompression.compress(bookFile.name(), bookFile.content()));
        bookFileRepository.save(bookFile);
        eventService.publish(BookFileAddedEvent.of(bookFile));
        return bookFileId;
    }

    public void deleteBookFile(BookFileId bookFileId) {
        var bookFile = bookFileRepository.find(bookFileId);
        bookFileRepository.delete(bookFile);
        eventService.publish(BookFileDeletedEvent.of(bookFile));
    }

    public BookFileSearchStatus searchBookFile(BookId bookId) {
        Book book = bookRepository.find(bookId);
        return bookFileSearch.searchBookFile(book);
    }
}
