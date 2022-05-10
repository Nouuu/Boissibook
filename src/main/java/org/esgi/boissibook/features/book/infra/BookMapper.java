package org.esgi.boissibook.features.book.infra;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.infra.repository.BookEntity;
import org.esgi.boissibook.features.book.infra.web.response.BookResponse;

public class BookMapper {
    private BookMapper() {
    }

    public static BookEntity mapBookToBookEntity(Book book) {
        return new BookEntity(book.id(), book.apiId(), book.title(), book.authors(), book.publisher(),
            book.publishedDate(), book.description(), book.isbn13(), book.language(), book.imgUrl(), book.pages());
    }

    public static Book mapBookEntityToBook(BookEntity bookEntity) {
        return new Book(bookEntity.id(), bookEntity.apiId(), bookEntity.title(), bookEntity.authors(), bookEntity.publisher(),
            bookEntity.publishedDate(), bookEntity.description(), bookEntity.isbn13(), bookEntity.language(), bookEntity.imgUrl(), bookEntity.pages());
    }

    public static BookResponse mapBookToBookResponse(Book book) {
        return new BookResponse(book.id(), book.apiId(), book.title(), book.authors(), book.publisher(), book.publishedDate(), book.description(), book.isbn13(), book.language(), book.imgUrl(), book.pages());
    }

    public static Book mapBookSearchToBook(org.esgi.boissibook.features.book_search.domain.Book bookSearch) {
        return new Book(null, bookSearch.id(), bookSearch.title(), bookSearch.authors(), bookSearch.publisher(), bookSearch.publishedDate(), bookSearch.description(), bookSearch.isbn13(), bookSearch.language(), bookSearch.imgUrl(), bookSearch.pages());
    }
}
