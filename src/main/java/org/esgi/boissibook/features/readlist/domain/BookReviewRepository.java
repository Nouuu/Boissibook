package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.kernel.repository.BookReviewId;

import java.util.List;

public interface BookReviewRepository {
    BookReviewId nextId();

    void save(BookReview bookReview);

    BookReview find(BookReviewId id);

    void delete(BookReview bookReview);

    BookReview findByBookIdAndUserId(String bookId, String userId);

    List<BookReview> findByUserId(String userId);
}
