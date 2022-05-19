package org.esgi.boissibook.features.book_file.domain;

import org.esgi.boissibook.features.book_file.domain.event.BookFileAddedEvent;
import org.esgi.boissibook.features.book_file.domain.event.BookFileDeletedEvent;
import org.esgi.boissibook.kernel.event.EventService;

public final class BookFileCommandHandler {
    private final BookFileRepository bookFileRepository;
    private final EventService eventService;
    private final FileCompression fileCompression;

    public BookFileCommandHandler(BookFileRepository bookFileRepository, EventService eventService, FileCompression fileCompression) {
        this.bookFileRepository = bookFileRepository;
        this.eventService = eventService;
        this.fileCompression = fileCompression;
    }

    public String createBookFile(BookFile bookFile) {
        String bookFileId = bookFileRepository.nextId();
        bookFile.setId(bookFileId);
        bookFile.setContent(fileCompression.compress(bookFile.name(), bookFile.content()));
        bookFileRepository.save(bookFile);
        eventService.publish(BookFileAddedEvent.of(bookFile));
        return bookFileId;
    }

    public void deleteBookFile(String bookFileId) {
        var bookFile = bookFileRepository.find(bookFileId);
        bookFileRepository.delete(bookFile);
        eventService.publish(BookFileDeletedEvent.of(bookFile));
    }
}
