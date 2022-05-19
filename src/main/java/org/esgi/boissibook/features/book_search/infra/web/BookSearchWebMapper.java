package org.esgi.boissibook.features.book_search.infra.web;

import java.util.List;
import org.esgi.boissibook.features.book_search.domain.BookSearchItem;

public class BookSearchWebMapper {

    private BookSearchWebMapper() {
    }

    static BooksResponse toBooksResponse(List<BookSearchItem> bookSearchItems) {
        return new BooksResponse(bookSearchItems.stream().map(BookSearchWebMapper::toBookResponse).toList());
    }

    static BookResponse toBookResponse(BookSearchItem bookSearchItem) {
        return new BookResponse(
            bookSearchItem.id(),
            bookSearchItem.title(),
            bookSearchItem.authors(),
            bookSearchItem.publisher(),
            bookSearchItem.publishedDate(),
            bookSearchItem.description(),
            bookSearchItem.isbn13(),
            bookSearchItem.language(),
            bookSearchItem.imgUrl(),
            bookSearchItem.pages()
        );
    }
}
