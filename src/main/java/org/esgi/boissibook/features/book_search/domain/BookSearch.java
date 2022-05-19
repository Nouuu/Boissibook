package org.esgi.boissibook.features.book_search.domain;

import java.util.List;

public interface BookSearch {
    List<BookSearchItem> searchBooks(String query);

    BookSearchItem getBook(String id);

}
