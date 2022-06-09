package org.esgi.boissibook.features.book.domain;

import org.esgi.boissibook.kernel.repository.BookId;

import java.util.List;

public class BookQueryHandler {
    private final BookRepository bookRepository;

    public BookQueryHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(BookId bookId) {
        return bookRepository.find(bookId);
    }
}
