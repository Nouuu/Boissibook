package org.esgi.boissibook.features.book_file.infra.repository;

import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.esgi.boissibook.features.book_file.domain.BookFileRepository;
import org.esgi.boissibook.features.book_file.infra.BookFileMapper;
import org.esgi.boissibook.features.book_file.kernel.exception.BookFileExceptionMessage;
import org.esgi.boissibook.features.book_file.kernel.exception.BookFileNotFoundException;

import java.util.List;
import java.util.UUID;

public class SpringDataBookFileRepository implements BookFileRepository {
    private final JPABookFileRepository bookFileRepository;

    public SpringDataBookFileRepository(JPABookFileRepository bookFileRepository) {
        this.bookFileRepository = bookFileRepository;
    }

    @Override
    public String save(BookFile bookFile) {
        return bookFileRepository.save(BookFileMapper.mapBookFileToBookFileEntity(bookFile)).id();
    }

    @Override
    public long count() {
        return bookFileRepository.count();
    }

    @Override
    public List<BookFile> findAll() {
        return bookFileRepository.findAll().stream().map(BookFileMapper::mapEntityBookFileToBookFile).toList();
    }

    @Override
    public BookFile find(String id) throws BookFileNotFoundException {
        return BookFileMapper.mapEntityBookFileToBookFile(bookFileRepository.findById(id)
            .orElseThrow(() -> new BookFileNotFoundException(String.format("%s : %s", BookFileExceptionMessage.BOOK_FILE_NOT_FOUND, id))));
    }

    @Override
    public void delete(BookFile bookFile) {
        bookFileRepository.delete(BookFileMapper.mapBookFileToBookFileEntity(bookFile));
    }

    @Override
    public void deleteAll() {
        bookFileRepository.deleteAll();
    }

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<BookFile> findByBookId(String bookId) {
        return bookFileRepository.findByBookId(bookId).stream()
            .map(BookFileMapper::mapEntityBookFileToBookFile)
            .toList();
    }

    @Override
    public long countAllByBookId(String bookId) {
        return bookFileRepository.countAllByBookId(bookId);
    }
}
