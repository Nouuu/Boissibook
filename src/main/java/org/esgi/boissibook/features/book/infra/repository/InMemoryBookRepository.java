package org.esgi.boissibook.features.book.infra.repository;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.book.kernel.exception.BookConflictException;
import org.esgi.boissibook.features.book.kernel.exception.BookExceptionMessage;
import org.esgi.boissibook.features.book.kernel.exception.BookNotFoundException;
import org.esgi.boissibook.infra.repository.InMemoryRepository;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;

import java.util.Objects;

public class InMemoryBookRepository extends InMemoryRepository<Book, BookId> implements BookRepository {

    private static final String FORMATTED_EXCEPTION = "%s %s";

    @Override
    public BookId save(Book book) {
        if (this.data.values().stream().anyMatch(book1 -> book.apiId().equals(book1.apiId()))) {
            throw new BookConflictException(String.format(FORMATTED_EXCEPTION, BookExceptionMessage.BOOK_CONFLICT, book.apiId()));
        }
        return super.save(book);
    }

    @Override
    public Book find(BookId id) throws NotFoundException {
        var result = data.get(Objects.requireNonNull(id, "Id can't be null").value());
        if (result == null) {
            throw new BookNotFoundException(String.format(FORMATTED_EXCEPTION, BookExceptionMessage.BOOK_NOT_FOUND, id));
        }
        return result;
    }

    @Override
    public void delete(Book entity) throws NotFoundException {
        if (data.remove(Objects.requireNonNull(entity.id(), "Id can't be null").value()) == null) {
            throw new BookNotFoundException(String.format(FORMATTED_EXCEPTION, BookExceptionMessage.BOOK_NOT_FOUND, entity.id()));
        }
    }

    @Override
    public BookId nextId() {
        return BookId.nextId();
    }

    @Override
    public Book findByApiId(String apiId) {
        return this.data.values().stream().filter(book -> book.apiId().equals(apiId))
            .findFirst()
            .orElseThrow(() -> new BookNotFoundException(String.format(FORMATTED_EXCEPTION, BookExceptionMessage.BOOK_NOT_FOUND, apiId)));
    }
}
