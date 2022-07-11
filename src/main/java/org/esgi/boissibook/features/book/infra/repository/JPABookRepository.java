package org.esgi.boissibook.features.book.infra.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPABookRepository extends JpaRepository<BookEntity, String> {
    Optional<BookEntity> findByApiId(String apiId);
}
