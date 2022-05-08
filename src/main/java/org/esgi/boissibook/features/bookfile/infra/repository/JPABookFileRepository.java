package org.esgi.boissibook.features.bookfile.infra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPABookFileRepository extends JpaRepository<BookFileEntity, String> {
    List<BookFileEntity> findByBookId(String bookId);
}
