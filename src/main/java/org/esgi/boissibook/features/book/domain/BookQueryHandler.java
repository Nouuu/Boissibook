package org.esgi.boissibook.features.book.domain;

import java.util.List;

public class BookQueryHandler {
    private final BookRepository bookRepository;

    public BookQueryHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(String bookId) {
        return bookRepository.find(bookId);
    }
}
