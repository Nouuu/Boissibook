package org.esgi.boissibook.features.book.infra;

import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.infra.repository.BookEntity;
import org.esgi.boissibook.features.book.infra.web.response.BookResponse;
import org.esgi.boissibook.features.book_search.domain.BookSearchItem;
import org.esgi.boissibook.kernel.repository.BookId;

public class BookMapper {
    private BookMapper() {
    }

    public static BookEntity mapBookToBookEntity(Book book) {
        return new BookEntity(book.id().value(), book.apiId(), book.title(), book.authors(), book.publisher(),
            book.publishedDate(), book.description(), book.isbn13(), book.language(), book.imgUrl(), book.pages());
    }

    public static Book mapBookEntityToBook(BookEntity bookEntity) {
        return new Book(BookId.of(bookEntity.id()), bookEntity.apiId(), bookEntity.title(), bookEntity.authors(), bookEntity.publisher(),
            bookEntity.publishedDate(), bookEntity.description(), bookEntity.isbn13(), bookEntity.language(), bookEntity.imgUrl(), bookEntity.pages());
    }

    public static BookResponse mapBookToBookResponse(Book book) {
        return new BookResponse(book.id().value(), book.apiId(), book.title(), book.authors(), book.publisher(), book.publishedDate(), book.description(), book.isbn13(), book.language(), book.imgUrl(), book.pages());
    }

    public static Book mapBookSearchToBook(BookSearchItem bookSearchItem) {
        return new Book(null, bookSearchItem.id(), bookSearchItem.title(), bookSearchItem.authors(), bookSearchItem.publisher(), bookSearchItem.publishedDate(), bookSearchItem.description(), bookSearchItem.isbn13(), bookSearchItem.language(), bookSearchItem.imgUrl(), bookSearchItem.pages());
    }
}
