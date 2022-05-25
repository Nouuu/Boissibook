package org.esgi.boissibook.features.readlist.infra.repository;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.BookReviewRepository;
import org.esgi.boissibook.features.readlist.infra.mapper.ReviewMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpringDataBookReviewRepository implements BookReviewRepository {
    private final JPABookReviewRepository bookReviewRepository;

    public SpringDataBookReviewRepository(JPABookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void save(BookReview bookReview) {
        var bookReviewEntity = ReviewMapper.toEntity(bookReview);
        bookReviewRepository.save(bookReviewEntity);
    }

    @Override
    public Optional<BookReview> find(String bookReviewId) {
        return Optional.empty();
    }

    @Override
    public void delete(BookReview bookReview) {
        var bookReviewEntity = ReviewMapper.toEntity(bookReview);
        bookReviewRepository.delete(bookReviewEntity);
    }

    @Override
    public Optional<BookReview> findByBookIdAndUserId(String bookId, String userId) {
        return bookReviewRepository.findByBookIdAndUserId(bookId, userId).map(ReviewMapper::fromEntity);
    }

    @Override
    public List<BookReview> findByUserId(String userId) {
       return bookReviewRepository
               .findByUserId(userId).stream()
               .map(ReviewMapper::fromEntity)
               .collect(Collectors.toList());
    }
}
