package org.esgi.boissibook.features.book.infra.repository;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.book.infra.BookMapper;
import org.esgi.boissibook.features.book.kernel.exception.BookConflictException;
import org.esgi.boissibook.features.book.kernel.exception.BookExceptionMessage;
import org.esgi.boissibook.features.book.kernel.exception.BookNotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class SpringDataBookRepository implements BookRepository {

    private static final String FORMATTED_EXCEPTION = "%s : %s";
    private final JPABookRepository bookRepository;

    public SpringDataBookRepository(JPABookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookId save(Book book) throws BookConflictException {
        BookEntity bookEntity = BookMapper.mapBookToBookEntity(book);
        try {
            bookRepository.save(bookEntity).id();
            return book.id();
        } catch (DataIntegrityViolationException e) {
            throw new BookConflictException(String.format(FORMATTED_EXCEPTION, BookExceptionMessage.BOOK_CONFLICT, book.apiId()));
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
    public Book find(BookId id) throws BookNotFoundException {
        return BookMapper.mapBookEntityToBook(bookRepository.findById(id.value()).orElseThrow(() -> new BookNotFoundException(String.format(FORMATTED_EXCEPTION, BookExceptionMessage.BOOK_NOT_FOUND, id))));
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
    public BookId nextId() {
        return BookId.nextId();
    }

    @Override
    public Book findByApiId(String apiId) {
        return BookMapper.mapBookEntityToBook(bookRepository.findByApiId(apiId).orElseThrow(() -> new BookNotFoundException(String.format(FORMATTED_EXCEPTION, BookExceptionMessage.BOOK_NOT_FOUND, apiId))));
    }
}
