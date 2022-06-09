package org.esgi.boissibook.features.book_search.infra;

import jdk.jfr.Description;
import org.esgi.boissibook.features.book_search.domain.BookSearchItem;
import org.esgi.boissibook.features.book_search.infra.models.BookItem;
import org.esgi.boissibook.features.book_search.infra.models.BookSearchResponse;
import org.esgi.boissibook.features.book_search.infra.models.ImageLinks;
import org.esgi.boissibook.features.book_search.infra.models.IndustryIdentifier;
import org.esgi.boissibook.features.book_search.infra.models.VolumeInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookItemMapperTest {

    BookItem bookItem1 = new BookItem("1", "title1",
            new VolumeInfo("title1", List.of("author1"), "publisher1", "2000", "description1",
                    List.of(new IndustryIdentifier("ISBN_13", "978-1-56619-909-4")), 100, List.of("category1"),
                    4.5f, 125, new ImageLinks("imageUrl1", "imageUrl1"), "fr"));

    BookItem bookItem2 = new BookItem("2", "title2",
            new VolumeInfo("title2", List.of("author2"), "publisher2", "2001", "description2",
                    List.of(new IndustryIdentifier("ISBN_23", "978-2-56629-909-4")), 200, List.of("category2"),
                    4.5f, 225, new ImageLinks("imageUrl2", "imageUrl2"), "fr"));

    BookSearchItem bookSearchItem1 = new BookSearchItem("1", "title1", List.of("author1"), "publisher1", "2000", "description1", "978-1-56619-909-4", "fr", "imageUrl1", 100);


    @Test
    void toBook1() {
        assertThat(BookItemMapper.toBook(bookItem1)).isEqualTo(bookSearchItem1);
    }
    @Test
    @DisplayName("Book without isbn 13 should return null")
    void toBook2() {
        assertThat(BookItemMapper.toBook(bookItem2)).isNull();
    }

    @Test
    @DisplayName("List with one valid book and one invalid book should return list of one valid book")
    void toBookList() {
        assertThat(BookItemMapper.toBookList(new BookSearchResponse(2, List.of(bookItem1, bookItem2))))
                .hasSize(1)
                .containsOnly(bookSearchItem1);
    }
}