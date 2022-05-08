package org.esgi.boissibook.features.book_search.infrastructure;

import org.esgi.boissibook.features.book_search.domain.Book;
import org.esgi.boissibook.features.book_search.infrastructure.models.BookItem;
import org.esgi.boissibook.features.book_search.infrastructure.models.IndustryIdentifier;

import java.util.List;

public final class BookItemMapper {

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
                bookItem.volumeInfo().imageLinks().thumbnail(),
                bookItem.volumeInfo().pageCount()
        );
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
