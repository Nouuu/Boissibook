package org.esgi.boissibook.features.book.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPABookRepository extends JpaRepository<BookEntity, String> {
}
