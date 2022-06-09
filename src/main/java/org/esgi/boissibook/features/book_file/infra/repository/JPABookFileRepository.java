package org.esgi.boissibook.features.book_file.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPABookFileRepository extends JpaRepository<BookFileEntity, String> {
    List<BookFileEntity> findByBookId(String bookId);

    long countAllByBookId(String bookId);
}
