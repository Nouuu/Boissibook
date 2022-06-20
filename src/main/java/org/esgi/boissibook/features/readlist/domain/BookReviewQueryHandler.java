package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.kernel.repository.UserId;

import java.util.List;
import java.util.Optional;

public class BookReviewQueryHandler {
    private final BookReviewRepository bookReviewRepository;

    public BookReviewQueryHandler(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    public BookReview getBookReviewById(BookReviewId id) {
        return bookReviewRepository.find(id);
    }

    public BookReview getBookReviewByBookIdAndUserId(BookId bookId, UserId userId) {
        return bookReviewRepository.findByBookIdAndUserId(bookId, userId);
    }

    public boolean isAlreadyReviewByUser(BookId bookId, UserId userId) {
        Optional<BookReview> review = bookReviewRepository.findByBookAndUserId(bookId, userId);
        return review.isPresent();
    }

    public List<BookReview> getAllReviewOfAUser(UserId userId) {
        return bookReviewRepository.findByUserId(userId);
    }
}
