package org.esgi.boissibook.features.readlist.infra.repository;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.features.readlist.domain.BookReviewRepository;
import org.esgi.boissibook.features.readlist.infra.mapper.ReviewMapper;
import org.esgi.boissibook.features.readlist.kernel.exception.BookReviewExceptionMessage;
import org.esgi.boissibook.features.readlist.kernel.exception.BookReviewNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
    public void save(BookReview bookReview) {
        var bookReviewEntity = ReviewMapper.toEntity(bookReview);
        bookReviewRepository.save(bookReviewEntity);
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
        var bookReviewEntity = ReviewMapper.toEntity(bookReview);
        bookReviewRepository.delete(bookReviewEntity);
    }

    @Override
    public BookReview findByBookIdAndUserId(String bookId, String userId) {
        return ReviewMapper.fromEntity(bookReviewRepository.findByBookIdAndUserId(bookId, userId)
                .orElseThrow(() -> new BookReviewNotFoundException(
                        String.format("%s : %s", BookReviewExceptionMessage.REVIEW_NOT_FOUND, bookId)
                ))
        );
    }

    @Override
    public List<BookReview> findByUserId(String userId) {
       return bookReviewRepository
               .findByUserId(userId).stream()
               .map(ReviewMapper::fromEntity)
               .collect(Collectors.toList());
    }
}