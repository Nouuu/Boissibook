package org.esgi.boissibook.features.book_search.infra.web;

import org.esgi.boissibook.features.book_search.domain.Book;

import java.util.List;

public class BookSearchWebMapper {

    private BookSearchWebMapper() {
    }

    static BooksResponse toBooksResponse(List<Book> books) {
        return new BooksResponse(books.stream().map(BookSearchWebMapper::toBookResponse).toList());
    }

    static BookResponse toBookResponse(Book book) {
        return new BookResponse(
                book.id(),
                book.title(),
                book.authors(),
                book.publisher(),
                book.publishedDate(),
                book.description(),
                book.isbn13(),
                book.language(),
                book.imgUrl(),
                book.pages()
        );
    }
}
