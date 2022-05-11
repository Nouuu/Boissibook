package org.esgi.boissibook.features.book_search.infra;

import org.esgi.boissibook.features.book_search.domain.Book;
import org.esgi.boissibook.features.book_search.infra.models.BookItem;
import org.esgi.boissibook.features.book_search.infra.models.BookSearchResponse;
import org.esgi.boissibook.features.book_search.infra.models.IndustryIdentifier;

import java.util.List;

public final class BookItemMapper {
    private BookItemMapper() {
    }

    public static List<Book> toBookList(BookSearchResponse response) {
        return response.items()
                .stream()
                .map(BookItemMapper::toBook)
                .toList();
    }

    static Book toBook(BookItem bookItem) {
        return new Book(
                bookItem.id(),
                bookItem.volumeInfo().title(),
                bookItem.volumeInfo().authors(),
                bookItem.volumeInfo().publisher(),
                bookItem.volumeInfo().publishedDate(),
                bookItem.volumeInfo().description(),
                findISBN(bookItem.volumeInfo().industryIdentifiers()),
                bookItem.volumeInfo().language(),
                getThumbnail(bookItem),
                bookItem.volumeInfo().pageCount()
        );
    }

    private static String getThumbnail(BookItem bookItem) {
        if (bookItem.volumeInfo().imageLinks() != null) {
            return bookItem.volumeInfo().imageLinks().thumbnail();
        }
        return null;
    }

    private static String findISBN(List<IndustryIdentifier> industryIdentifiers) {
        for (IndustryIdentifier industryIdentifier : industryIdentifiers) {
            if (industryIdentifier.type().equals("ISBN_13")) {
                return industryIdentifier.identifier();
            }
        }
        return null;
    }
}