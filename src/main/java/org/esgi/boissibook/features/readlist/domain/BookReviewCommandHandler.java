package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.features.readlist.domain.event.ReviewStatusChangedEvent;
import org.esgi.boissibook.features.readlist.domain.event.UserAddBookReviewEvent;
import org.esgi.boissibook.features.readlist.domain.event.UserDeleteReviewEvent;
import org.esgi.boissibook.kernel.event.EventService;
import org.esgi.boissibook.kernel.repository.BookReviewId;

public class BookReviewCommandHandler {
    private final BookReviewRepository bookReviewRepository;
    private final EventService eventService;

    public BookReviewCommandHandler(BookReviewRepository bookReviewRepository, EventService eventService) {
        this.bookReviewRepository = bookReviewRepository;
        this.eventService = eventService;
    }

    public BookReviewId createReview(BookReview bookReview) {
        BookReviewId bookId = bookReviewRepository.nextId();
        bookReview.setBookReviewId(bookId);
        bookReviewRepository.save(bookReview);
        eventService.publish(UserAddBookReviewEvent.of(bookReview));
        return bookId;
    }

    public void deleteReview(BookReviewId bookReviewId) {
        var bookReview = bookReviewRepository.find(bookReviewId);
        bookReviewRepository.delete(bookReview);
        eventService.publish(UserDeleteReviewEvent.of(bookReview));
    }

    public void updateReview(BookReviewId id, BookReview updateReview) {
        var bookReview = bookReviewRepository.find(id);
        updateReview.setBookReviewId(bookReview.id());
        bookReviewRepository.save(updateReview);
    }

    public void updateComment(BookReviewId id, String comment) {
        var bookReview = bookReviewRepository.find(id);
        bookReview.setComment(comment);
        bookReviewRepository.save(bookReview);
    }

    public void updateStatus(BookReviewId id, String status) {
        var bookReview = bookReviewRepository.find(id);
        bookReview.setReadingStatus(ReadingStatus.valueOf(status));
        eventService.publish(ReviewStatusChangedEvent.of(bookReview));
        bookReviewRepository.save(bookReview);
    }

    public void updateCurrentPage(BookReviewId id, int currentPage) {
        var bookReview = bookReviewRepository.find(id);
        bookReview.setCurrentPage(currentPage);
        bookReviewRepository.save(bookReview);
    }

    public void updateRating(BookReviewId id, int newNote) {
        var bookReview = bookReviewRepository.find(id);
        bookReview.setNote(newNote);
        bookReviewRepository.save(bookReview);
    }
}
