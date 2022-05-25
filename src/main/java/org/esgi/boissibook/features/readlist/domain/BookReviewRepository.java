package org.esgi.boissibook.features.readlist.domain;

import java.util.List;
import java.util.Optional;

public interface BookReviewRepository {
    String nextId();

    void save(BookReview bookReview);

    Optional<BookReview> find(String bookReviewId);

    void delete(BookReview bookReview);

    BookReview findByBookIdAndUserId(String bookId, String userId);

    List<BookReview> findByUserId(String userId);
}
