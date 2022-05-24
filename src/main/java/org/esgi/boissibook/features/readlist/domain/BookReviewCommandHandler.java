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

    public void updateReview(String id, BookReview updateReview) {
        var optionalBookReview = bookReviewRepository.find(id);
        if (optionalBookReview.isEmpty()) {
            throw new IllegalArgumentException("BookReview not found");
        }
        var bookReview = optionalBookReview.get();
        updateReview.setBookReviewId(bookReview.getBookReviewId());
        bookReviewRepository.save(updateReview);
    }
    public void updateComment(String id, String comment) {
        var optionalBookReview = bookReviewRepository.find(id);
        if (optionalBookReview.isEmpty()) {
            throw new IllegalArgumentException("BookReview not found");
        }
        var bookReview = optionalBookReview.get();
        bookReview.setComment(comment);
        bookReviewRepository.save(bookReview);
    }

    public void updateStatus(String id, String status) {
        var optionalBookReview = bookReviewRepository.find(id);
        if (optionalBookReview.isEmpty()) {
            throw new IllegalArgumentException("BookReview not found");
        }
        var bookReview = optionalBookReview.get();
        bookReview.setReadingStatus(ReadingStatus.valueOf(status));
        bookReviewRepository.save(bookReview);
    }

    public void updateCurrentPage(String id, int currentPage) {
        var optionalBookReview = bookReviewRepository.find(id);
        if (optionalBookReview.isEmpty()) {
            throw new IllegalArgumentException("BookReview not found");
        }
        var bookReview = optionalBookReview.get();
        bookReview.setCurrentPage(currentPage);
        bookReviewRepository.save(bookReview);
    }

    public void updateRating(String id, int newNote) {
        var optionalBookReview = bookReviewRepository.find(id);
        if (optionalBookReview.isEmpty()) {
            throw new IllegalArgumentException("BookReview not found");
        }
        var bookReview = optionalBookReview.get();
        bookReview.setNote(newNote);
        bookReviewRepository.save(bookReview);
    }
}
