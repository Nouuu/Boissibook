package org.esgi.boissibook.features.book.infra.repository;

import java.util.Optional;
import org.esgi.boissibook.features.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPABookRepository extends JpaRepository<BookEntity, String> {
    Optional<Book> findByApiId(String apiId);

}
