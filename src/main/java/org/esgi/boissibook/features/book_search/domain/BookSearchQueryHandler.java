package org.esgi.boissibook.features.book_search.domain;

import java.util.List;

public class BookSearchQueryHandler {

    private final BookSearch bookSearch;

    public BookSearchQueryHandler(BookSearch bookSearch) {
        this.bookSearch = bookSearch;
    }

    public List<BookSearchItem> searchBooks(String query) {
        return bookSearch.searchBooks(query);
    }

    public BookSearchItem getBook(String id) {
        return bookSearch.getBook(id);
    }
}
