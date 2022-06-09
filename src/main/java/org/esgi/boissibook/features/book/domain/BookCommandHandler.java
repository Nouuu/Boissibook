package org.esgi.boissibook.features.book.domain;

import org.esgi.boissibook.features.book.domain.event.BookCreatedEvent;
import org.esgi.boissibook.features.book.domain.event.BookDeletedEvent;
import org.esgi.boissibook.features.book.kernel.exception.BookConflictException;
import org.esgi.boissibook.kernel.event.EventService;
import org.esgi.boissibook.kernel.repository.BookId;

public class BookCommandHandler {
    private final BookRepository bookRepository;
    private final EventService eventService;

    public BookCommandHandler(BookRepository bookRepository, EventService eventService) {
        this.bookRepository = bookRepository;
        this.eventService = eventService;
    }

    public BookId addBook(Book book) throws BookConflictException {
        BookId bookId = bookRepository.nextId();
        book.setId(bookId);
        eventService.publish(BookCreatedEvent.of(book));
        return bookRepository.save(book);
    }

    public void deleteBook(BookId bookId) {
        Book book = bookRepository.find(bookId);
        bookRepository.delete(book);
        eventService.publish(BookDeletedEvent.of(book));
    }
}
