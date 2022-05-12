package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.features.readlist.domain.event.UserAddBookReviewEvent;
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
}
