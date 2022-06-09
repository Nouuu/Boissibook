package org.esgi.boissibook.features.book.infra.repository;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.book.infra.BookMapper;
import org.esgi.boissibook.features.book.kernel.exception.BookConflictException;
import org.esgi.boissibook.features.book.kernel.exception.BookExceptionMessage;
import org.esgi.boissibook.features.book.kernel.exception.BookNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public class SpringDataBookRepository implements BookRepository {
    private final JpaRepository<BookEntity, String> bookRepository;

    public SpringDataBookRepository(JPABookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public String save(Book book) throws BookConflictException {
        BookEntity bookEntity = BookMapper.mapBookToBookEntity(book);
        try {
            return bookRepository.save(bookEntity).id();
        } catch (DataIntegrityViolationException e) {
            throw new BookConflictException(String.format("%s : %s", BookExceptionMessage.BOOK_CONFLICT, book.apiId()));
        }
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll().stream()
            .map(BookMapper::mapBookEntityToBook)
            .toList();
    }

    @Override
    public Book find(String id) throws BookNotFoundException {
        return BookMapper.mapBookEntityToBook(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(String.format("%s : %s", BookExceptionMessage.BOOK_NOT_FOUND, id))));
    }

    @Override
    public void delete(Book book) {
        BookEntity bookEntity = BookMapper.mapBookToBookEntity(book);
        bookRepository.delete(bookEntity);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }
}
