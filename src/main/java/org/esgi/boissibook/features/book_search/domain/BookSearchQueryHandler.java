package org.esgi.boissibook.features.book_search.domain;

import org.esgi.boissibook.features.book_search.infrastructure.BookItemMapper;
import org.esgi.boissibook.features.book_search.infrastructure.BookSearchResponseMapper;

import java.util.List;

public class BookSearchQueryHandler {

    private final BookSearch bookSearch;

    public BookSearchQueryHandler(BookSearch bookSearch) {
        this.bookSearch = bookSearch;
    }

    public List<Book> searchBooks(String query) {
        return bookSearch.searchBooks(query);
    }

    public Book getBook(String id) {
        return bookSearch.getBook(id);
    }
}
