package org.esgi.boissibook.features.readlist.infra.repository;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.BookReviewRepository;
import org.esgi.boissibook.features.readlist.infra.mapper.ReviewMapper;
import org.esgi.boissibook.features.readlist.kernel.exception.BookReviewExceptionMessage;
import org.esgi.boissibook.features.readlist.kernel.exception.BookReviewNotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.kernel.repository.UserId;

import java.util.List;
import java.util.Optional;

public class SpringDataBookReviewRepository implements BookReviewRepository {
    private final JPABookReviewRepository bookReviewRepository;

    public SpringDataBookReviewRepository(JPABookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    @Override
    public BookReviewId nextId() {
        return BookReviewId.nextId();
    }

    @Override
    public BookReviewId save(BookReview bookReview) {
        var bookReviewEntity = ReviewMapper.toEntity(bookReview);
        bookReviewRepository.save(bookReviewEntity);
        return bookReview.id();
    }

    @Override
    public long count() {
        return bookReviewRepository.count();
    }

    @Override
    public List<BookReview> findAll() {
        return bookReviewRepository.findAll().stream()
            .map(ReviewMapper::fromEntity)
            .toList();
    }

    @Override
    public BookReview find(BookReviewId bookReviewId) {
        return ReviewMapper.fromEntity(bookReviewRepository.findById(bookReviewId.value())
            .orElseThrow(() -> new BookReviewNotFoundException(
                String.format("%s : %s", BookReviewExceptionMessage.REVIEW_NOT_FOUND, bookReviewId)
            ))
        );
    }

    @Override
    public void delete(BookReview bookReview) {
        bookReviewRepository.delete(
            bookReviewRepository.findById(bookReview.id().value())
                .orElseThrow(() -> new BookReviewNotFoundException(
                    String.format("%s : %s", BookReviewExceptionMessage.REVIEW_NOT_FOUND, bookReview.id())
                ))
        );
    }

    @Override
    public void deleteAll() {
        bookReviewRepository.deleteAll();
    }

    @Override
    public BookReview findByBookIdAndUserId(BookId bookId, UserId userId) {
        return ReviewMapper.fromEntity(bookReviewRepository.findByBookIdAndUserId(bookId.value(), userId.value())
            .orElseThrow(() -> new BookReviewNotFoundException(
                String.format("%s : %s, %s", BookReviewExceptionMessage.REVIEW_NOT_FOUND, bookId, userId)
            ))
        );
    }

    @Override
    public Optional<BookReview> tryFindByBookIdAndUserId(BookId bookId, UserId userId) {
        var review = bookReviewRepository.findByBookIdAndUserId(bookId.value(), userId.value());
        return review.map(ReviewMapper::fromEntity);
    }

    @Override
    public List<BookReview> findByUserId(UserId userId) {
        return bookReviewRepository
            .findByUserId(userId.value()).stream()
            .map(ReviewMapper::fromEntity)
            .toList();
    }
}
