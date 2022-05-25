package org.esgi.boissibook.features.readlist.domain;

import java.util.List;

public class BookReviewQueryHandler {
    private final BookReviewRepository bookReviewRepository;

    public BookReviewQueryHandler(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    public BookReview getBookReviewById(String id) {
        var bookReview = bookReviewRepository.find(id);
        if (bookReview.isEmpty()) {
            throw new IllegalArgumentException(id);
        }
        return bookReview.get();
    }

    public BookReview getBookReviewByBookIdAndUserId(String bookId, String userId) {
        var bookReview = bookReviewRepository.findByBookIdAndUserId(bookId, userId);
        if (bookReview.isEmpty()) {
            throw new IllegalArgumentException(bookId + " " + userId);
        }
        return bookReview.get();
    }

    public List<BookReview> getAllReviewOfAUser(String userId) {
        return bookReviewRepository.findByUserId(userId);
    }
}
