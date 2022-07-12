package org.esgi.boissibook.features.book.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPABookRepository extends JpaRepository<BookEntity, String> {
    Optional<BookEntity> findByApiId(String apiId);
}
