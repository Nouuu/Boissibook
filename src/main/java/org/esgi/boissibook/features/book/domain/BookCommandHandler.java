package org.esgi.boissibook.features.book.domain;

import org.esgi.boissibook.features.book.domain.event.BookCreatedEvent;
import org.esgi.boissibook.features.book.domain.event.BookDeletedEvent;
import org.esgi.boissibook.features.book.kernel.exception.BookConflictException;
import org.esgi.boissibook.kernel.event.EventService;

public class BookCommandHandler {
    private final BookRepository bookRepository;
    private final EventService eventService;

    public BookCommandHandler(BookRepository bookRepository, EventService eventService) {
        this.bookRepository = bookRepository;
        this.eventService = eventService;
    }

    public String addBook(Book book) throws BookConflictException {
        String bookId = bookRepository.nextId();
        book.setId(bookId);
        eventService.publish(BookCreatedEvent.of(book));
        return bookRepository.save(book);
    }

    public void deleteBook(String bookId) {
        Book book = bookRepository.find(bookId);
        bookRepository.delete(book);
        eventService.publish(BookDeletedEvent.of(book));
    }
}
