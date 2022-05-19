package org.esgi.boissibook.features.readlist.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPABookReviewRepository extends JpaRepository<BookReviewEntity, String> {
}
