package org.esgi.boissibook.features.book_file.infra.repository;

import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.esgi.boissibook.features.book_file.domain.BookFileRepository;
import org.esgi.boissibook.features.book_file.kernel.exception.BookFileExceptionMessage;
import org.esgi.boissibook.features.book_file.kernel.exception.BookFileNotFoundException;
import org.esgi.boissibook.infra.repository.InMemoryRepository;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.repository.BookFileId;
import org.esgi.boissibook.kernel.repository.BookId;

import java.util.List;
import java.util.Objects;

public class InMemoryBookFileRepository extends InMemoryRepository<BookFile, BookFileId> implements BookFileRepository {

    @Override
    public BookFile find(BookFileId id) throws NotFoundException {
        var result = data.get(Objects.requireNonNull(id, "Id can't be null").value());
        if (result == null) {
            throw new BookFileNotFoundException(String.format("%s : %s", BookFileExceptionMessage.BOOK_FILE_NOT_FOUND, id));
        }
        return result;
    }

    @Override
    public void delete(BookFile entity) throws NotFoundException {
        if (data.remove(Objects.requireNonNull(entity.id(), "Id can't be null").value()) == null) {
            throw new BookFileNotFoundException(String.format("%s : %s", BookFileExceptionMessage.BOOK_FILE_NOT_FOUND, entity.id()));
        }
    }

    @Override
    public List<BookFile> findByBookId(BookId bookId) {
        return data.values()
            .stream()
            .filter(bookFile -> Objects.equals(bookFile.bookId(), bookId))
            .toList();
    }

    @Override
    public long countAllByBookId(BookId bookId) {
        return data.values()
            .stream()
            .filter(bookFile -> Objects.equals(bookFile.bookId(), bookId))
            .count();
    }

    @Override
    public BookFileId nextId() {
        return BookFileId.nextId();
    }
}
