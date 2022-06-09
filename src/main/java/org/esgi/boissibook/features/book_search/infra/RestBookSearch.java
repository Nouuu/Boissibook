package org.esgi.boissibook.features.book_search.infra;

import org.esgi.boissibook.features.book_search.domain.BookSearch;
import org.esgi.boissibook.features.book_search.domain.BookSearchItem;
import org.esgi.boissibook.features.book_search.infra.search_engine.RestSearchEngine;

import java.util.List;

public class RestBookSearch implements BookSearch {

    private final RestSearchEngine searchEngine;

    public RestBookSearch(RestSearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public List<BookSearchItem> searchBooks(String query) {
        return BookItemMapper.toBookList(searchEngine.search(query));
    }

    @Override
    public BookSearchItem getBook(String id) {
        return BookItemMapper.toBook(searchEngine.getBookItem(id));
    }
}
