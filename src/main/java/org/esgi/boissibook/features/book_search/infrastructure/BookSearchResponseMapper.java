package org.esgi.boissibook.features.book_search.infrastructure;

import org.esgi.boissibook.features.book_search.domain.Book;
import org.esgi.boissibook.features.book_search.infrastructure.models.BookSearchResponse;

import java.util.List;

public final class BookSearchResponseMapper {

    private BookSearchResponseMapper() {
    }

    public static List<Book> toBookList(BookSearchResponse response) {
        return response.items()
                .stream()
                .map(BookItemMapper::toBook)
                .toList();
    }
}
