package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.features.readlist.domain.event.UserAddBookReviewEvent;
import org.esgi.boissibook.features.readlist.domain.event.UserDeleteReviewEvent;
import org.esgi.boissibook.kernel.event.EventService;

public class BookReviewCommandHandler {
    private final BookReviewRepository bookReviewRepository;
    private final EventService eventService;

    public BookReviewCommandHandler(BookReviewRepository bookReviewRepository, EventService eventService) {
        this.bookReviewRepository = bookReviewRepository;
        this.eventService = eventService;
    }

    public String createReview(BookReview bookReview) {
        String bookId = bookReviewRepository.nextId();
        bookReview.setBookReviewId(bookId);
        bookReviewRepository.save(bookReview);
        eventService.publish(UserAddBookReviewEvent.of(bookReview));
        return bookId;
    }

    public void deleteReview(String bookReviewId) {
        var optionalBookReview = bookReviewRepository.find(bookReviewId);
        if (optionalBookReview.isEmpty()) {
            throw new IllegalArgumentException("BookReview not found");
        }
        var bookReview = optionalBookReview.get();
        bookReviewRepository.delete(bookReview);
        eventService.publish(UserDeleteReviewEvent.of(bookReview));
    }
}
