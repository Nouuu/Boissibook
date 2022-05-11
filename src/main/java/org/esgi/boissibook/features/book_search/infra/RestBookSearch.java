package org.esgi.boissibook.features.book_search.infra;

import org.esgi.boissibook.features.book_search.domain.Book;
import org.esgi.boissibook.features.book_search.domain.BookSearch;
import org.esgi.boissibook.features.book_search.infra.search_engine.RestSearchEngine;

import java.util.List;

public class RestBookSearch implements BookSearch {

    private final RestSearchEngine searchEngine;

    public RestBookSearch(RestSearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public List<Book> searchBooks(String query) {
        return BookItemMapper.toBookList(searchEngine.search(query));
    }

    @Override
    public Book getBook(String id) {
        return BookItemMapper.toBook(searchEngine.getBookItem(id));
    }
}