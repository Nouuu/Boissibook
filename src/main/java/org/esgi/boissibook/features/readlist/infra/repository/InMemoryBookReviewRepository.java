package org.esgi.boissibook.features.readlist.infra.repository;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.BookReviewRepository;
import org.esgi.boissibook.features.readlist.kernel.exception.BookReviewExceptionMessage;
import org.esgi.boissibook.features.readlist.kernel.exception.BookReviewNotFoundException;
import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.infra.repository.InMemoryRepository;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.kernel.repository.UserId;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InMemoryBookReviewRepository extends InMemoryRepository<BookReview, BookReviewId> implements BookReviewRepository {
    @Override
    public BookReview findByBookIdAndUserId(BookId bookId, UserId userId) {
        return data.values()
            .stream()
            .filter(bookReview -> Objects.equals(bookReview.getBookId(), bookId) && Objects.equals(bookReview.getUserId(), userId))
            .findFirst()
            .orElseThrow(() -> new BookReviewNotFoundException(String.format("%s : %s, %s", BookReviewExceptionMessage.REVIEW_NOT_FOUND, bookId, userId)));
    }

    @Override
    public Optional<BookReview> tryFindByBookIdAndUserId(BookId bookId, UserId userId) {
        return data.values()
            .stream()
            .filter(bookReview -> Objects.equals(bookReview.getBookId(), bookId) && Objects.equals(bookReview.getUserId(), userId))
            .findFirst();
    }

    @Override
    public List<BookReview> findByUserId(UserId userId) {
        return data.values()
            .stream()
            .filter(bookReview -> Objects.equals(bookReview.getUserId(), userId))
            .toList();
    }

    @Override
    public Optional<BookReview> findByBookAndUserId(BookId bookId, UserId userId) {
        return data.values()
            .stream()
            .filter(bookReview -> Objects.equals(bookReview.getBookId(), bookId) && Objects.equals(bookReview.getUserId(), userId))
            .findFirst();
    }

    @Override
    public BookReview find(BookReviewId id) throws NotFoundException {
        var result = data.get(Objects.requireNonNull(id, "Id can't be null").value());
        if (result == null) {
            throw new BookReviewNotFoundException(String.format("%s : %s", BookReviewExceptionMessage.REVIEW_NOT_FOUND, id));
        }
        return result;
    }

    @Override
    public void delete(BookReview entity) throws NotFoundException {
        if (data.remove(Objects.requireNonNull(entity.id(), "Id can't be null").value()) == null) {
            throw new BookReviewNotFoundException(String.format("%s : %s", BookReviewExceptionMessage.REVIEW_NOT_FOUND, entity.id()));
        }
    }

    @Override
    public BookReviewId nextId() {
        return BookReviewId.nextId();
    }
}
