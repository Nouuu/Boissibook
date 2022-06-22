package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.kernel.repository.Repository;
import org.esgi.boissibook.kernel.repository.UserId;

import java.util.List;
import java.util.Optional;

public interface BookReviewRepository extends Repository<BookReview, BookReviewId> {
    BookReview findByBookIdAndUserId(BookId bookId, UserId userId);

    List<BookReview> findByUserId(UserId userId);

    Optional<BookReview> tryFindByBookIdAndUserId(BookId bookId, UserId userId);
}
