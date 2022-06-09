package org.esgi.boissibook.features.book_search.infra;

import org.esgi.boissibook.features.book_search.domain.BookSearchItem;
import org.esgi.boissibook.features.book_search.infra.models.BookItem;
import org.esgi.boissibook.features.book_search.infra.models.BookSearchResponse;
import org.esgi.boissibook.features.book_search.infra.models.ImageLinks;
import org.esgi.boissibook.features.book_search.infra.models.IndustryIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class BookItemMapper {
    private BookItemMapper() {
    }

    public static List<BookSearchItem> toBookList(BookSearchResponse response) {
        if (response == null || response.items() == null) {
            return new ArrayList<>();
        }
        return response.items()
            .stream()
            .map(BookItemMapper::toBook)
            .filter(Objects::nonNull)
            .toList();
    }

    static BookSearchItem toBook(BookItem bookItem) {
        Objects.requireNonNull(bookItem.volumeInfo(), "Book volumeInfo cannot be null");
        var isbn = findISBN(bookItem.volumeInfo().industryIdentifiers());
        if (isbn == null) {
            return null;
        }
        return new BookSearchItem(
            bookItem.id(),
            bookItem.volumeInfo().title(),
            bookItem.volumeInfo().authors(),
            bookItem.volumeInfo().publisher(),
            bookItem.volumeInfo().publishedDate(),
            bookItem.volumeInfo().description(),
            isbn,
            bookItem.volumeInfo().language(),
            getThumbnail(bookItem.volumeInfo().imageLinks()),
            bookItem.volumeInfo().pageCount()
        );
    }

    private static String getThumbnail(ImageLinks imageLinks) {
        if (imageLinks != null) {
            return imageLinks.thumbnail();
        }
        return null;
    }

    private static String findISBN(List<IndustryIdentifier> industryIdentifiers) {
        if (industryIdentifiers != null) {
            for (IndustryIdentifier industryIdentifier : industryIdentifiers) {
                if (industryIdentifier.type().equals("ISBN_13")) {
                    return industryIdentifier.identifier();
                }
            }
        }
        return null;
    }
}
