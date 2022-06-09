package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.kernel.repository.BookReviewId;

import java.util.List;

public class BookReviewQueryHandler {
    private final BookReviewRepository bookReviewRepository;

    public BookReviewQueryHandler(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    public BookReview getBookReviewById(BookReviewId id) {
        return bookReviewRepository.find(id);
    }

    public BookReview getBookReviewByBookIdAndUserId(String bookId, String userId) {
        return bookReviewRepository.findByBookIdAndUserId(bookId, userId);
    }

    public List<BookReview> getAllReviewOfAUser(String userId) {
        return bookReviewRepository.findByUserId(userId);
    }
}
