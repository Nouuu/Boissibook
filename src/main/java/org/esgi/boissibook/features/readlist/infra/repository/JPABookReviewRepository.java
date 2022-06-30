package org.esgi.boissibook.features.readlist.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JPABookReviewRepository extends JpaRepository<BookReviewEntity, String> {
    Optional<BookReviewEntity> findByBookIdAndUserId(String bookId, String userId);

    List<BookReviewEntity> findByUserId(String userId);

    List<BookReviewEntity> findByBookId(String userId);
}
