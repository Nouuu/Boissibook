package org.esgi.boissibook.features.book_file.domain;

import java.util.List;
import org.esgi.boissibook.kernel.repository.Repository;

public interface BookFileRepository extends Repository<BookFile> {
    List<BookFile> findByBookId(String bookId);

}
