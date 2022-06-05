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
        BookReviewId bookId = bookReviewRepository.nextId();
        bookReview.setBookReviewId(bookId);
        bookReviewRepository.save(bookReview);
        eventService.publish(UserAddBookReviewEvent.of(bookReview));
        return bookId;
    }

    public void deleteReview(String bookReviewId) {
        var bookReview = bookReviewRepository.find(bookReviewId);
        bookReviewRepository.delete(bookReview);
        eventService.publish(UserDeleteReviewEvent.of(bookReview));
    }

    public void updateReview(String id, BookReview updateReview) {
        var bookReview = bookReviewRepository.find(id);
        updateReview.setBookReviewId(bookReview.getBookReviewId());
        bookReviewRepository.save(updateReview);
    }

    public void updateComment(String id, String comment) {
        var bookReview = bookReviewRepository.find(id);
        bookReview.setComment(comment);
        bookReviewRepository.save(bookReview);
    }

    public void updateStatus(String id, String status) {
        var bookReview = bookReviewRepository.find(id);
        bookReview.setReadingStatus(ReadingStatus.valueOf(status));
        bookReviewRepository.save(bookReview);
    }

    public void updateCurrentPage(String id, int currentPage) {
        var bookReview = bookReviewRepository.find(id);
        bookReview.setCurrentPage(currentPage);
        bookReviewRepository.save(bookReview);
    }

    public void updateRating(String id, int newNote) {
        var bookReview = bookReviewRepository.find(id);
        bookReview.setNote(newNote);
        bookReviewRepository.save(bookReview);
    }
}
