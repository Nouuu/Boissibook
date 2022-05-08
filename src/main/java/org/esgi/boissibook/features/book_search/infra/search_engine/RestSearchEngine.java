package org.esgi.boissibook.features.book_search.infra.search_engine;

import org.esgi.boissibook.features.book_search.infra.models.BookItem;
import org.esgi.boissibook.features.book_search.infra.models.BookSearchResponse;

public interface RestSearchEngine {
    BookSearchResponse search(String query);

    BookItem getBookItem(String id);
}
