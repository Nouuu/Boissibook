package org.esgi.boissibook.features.book_search.infrastructure.search_engine;

import org.esgi.boissibook.features.book_search.infrastructure.models.BookItem;
import org.esgi.boissibook.features.book_search.infrastructure.models.BookSearchResponse;

public interface RestSearchEngine {
    BookSearchResponse search(String query);

    BookItem getBookItem(String id);
}
