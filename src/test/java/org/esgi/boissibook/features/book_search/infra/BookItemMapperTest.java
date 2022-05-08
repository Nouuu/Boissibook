package org.esgi.boissibook.features.book_search.infrastructure;

import org.esgi.boissibook.features.book_search.domain.Book;
import org.esgi.boissibook.features.book_search.infrastructure.models.BookItem;
import org.esgi.boissibook.features.book_search.infrastructure.models.ImageLinks;
import org.esgi.boissibook.features.book_search.infrastructure.models.IndustryIdentifier;
import org.esgi.boissibook.features.book_search.infrastructure.models.VolumeInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookItemMapperTest {

    BookItem bookItem1 = new BookItem("1", "title1",
            new VolumeInfo("title1", List.of("author1"), "publisher1", "2000", "description1",
                    List.of(new IndustryIdentifier("ISBN_13", "978-1-56619-909-4")), 100, List.of("category1"),
                    4.5f, 125, new ImageLinks("imageUrl1", "imageUrl1"), "fr"));

    BookItem bookItem2 = new BookItem("2", "title2",
            new VolumeInfo("title2", List.of("author2"), "publisher2", "2001", "description2",
                    List.of(new IndustryIdentifier("ISBN_23", "978-2-56629-909-4")), 200, List.of("category2"),
                    4.5f, 225, new ImageLinks("imageUrl2", "imageUrl2"), "fr"));

    Book book1 = new Book("1", "title1", List.of("author1"), "publisher1", "2000", "description1", "978-1-56619-909-4", "fr", "imageUrl1", 100);
    Book book2 = new Book("2", "title2", List.of("author2"), "publisher2", "2001", "description2", null, "fr", "imageUrl2", 200);


    @Test
    void toBook1() {
        assertThat(BookItemMapper.toBook(bookItem1)).isEqualTo(book1);
    }
    @Test
    void toBook2() {
        assertThat(BookItemMapper.toBook(bookItem2)).isEqualTo(book2);
    }
}