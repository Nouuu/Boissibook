package org.esgi.boissibook.features.book.domain;

import org.esgi.boissibook.features.book.infra.BookReviewMapper;
import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.BookReviewRepository;
import org.esgi.boissibook.kernel.repository.BookId;

import java.util.List;

public class BookQueryHandler {
    private final BookRepository bookRepository;
    private final BookReviewRepository bookReviewRepository;

    public BookQueryHandler(BookRepository bookRepository, BookReviewRepository bookReviewRepository) {
        this.bookRepository = bookRepository;
        this.bookReviewRepository = bookReviewRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(BookId bookId) {
        return bookRepository.find(bookId);
    }

    public double getBookAverageRate(BookId bookId) {
        var reviews = bookReviewRepository.findByBookId(bookId);
        return reviews.stream().mapToInt(BookReview::getNote).average().orElse(0);
    }

    public List<Comment> getBookComments(BookId bookId) {
        var reviews = bookReviewRepository.findByBookId(bookId);
        return reviews.stream().map(BookReviewMapper::mapBookReviewToComment).toList();
    }
}
