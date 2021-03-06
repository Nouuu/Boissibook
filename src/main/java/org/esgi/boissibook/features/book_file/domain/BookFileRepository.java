package org.esgi.boissibook.features.book_file.domain;

import org.esgi.boissibook.kernel.repository.BookFileId;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.Repository;

import java.util.List;

public interface BookFileRepository extends Repository<BookFile, BookFileId> {
    List<BookFile> findByBookId(BookId bookId);

    long countAllByBookId(BookId bookId);
}
