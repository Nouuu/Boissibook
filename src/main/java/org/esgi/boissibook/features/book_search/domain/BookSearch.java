package org.esgi.boissibook.features.book_search.domain;

import java.util.List;

public interface BookSearch {
    List<Book> searchBooks(String query);

    Book getBook(String id);

}
