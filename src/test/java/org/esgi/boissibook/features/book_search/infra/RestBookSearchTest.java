package org.esgi.boissibook.features.book_search.infra;

import org.esgi.boissibook.features.book_search.domain.BookSearchItem;
import org.esgi.boissibook.features.book_search.infra.models.BookItem;
import org.esgi.boissibook.features.book_search.infra.models.BookSearchResponse;
import org.esgi.boissibook.features.book_search.infra.models.ImageLinks;
import org.esgi.boissibook.features.book_search.infra.models.IndustryIdentifier;
import org.esgi.boissibook.features.book_search.infra.models.VolumeInfo;
import org.esgi.boissibook.features.book_search.infra.search_engine.RestSearchEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class RestBookSearchTest {

    @MockBean
    RestSearchEngine restSearchEngine;

    RestBookSearch restBookSearch;

    BookItem bookItem1 = new BookItem("1", "title1",
        new VolumeInfo("title1", List.of("author1"), "publisher1", "2000", "imageUrl1",
            List.of(new IndustryIdentifier("ISBN_13", "978-1-56619-909-4")), 100, List.of("category1"),
            4.5f, 125, new ImageLinks("imageUrl1", "imageUrl2"), "fr"));

    BookItem bookItem2 = new BookItem("2", "title2",
        new VolumeInfo("title2", List.of("author2"), "publisher2", "2001", "imageUrl2",
            List.of(new IndustryIdentifier("ISBN_23", "978-2-56629-909-4")), 200, List.of("category2"),
            4.5f, 225, new ImageLinks("imageUrl2", "imageUrl2"), "fr"));

    BookItem bookItem3 = new BookItem("3", "title3",
        new VolumeInfo("title3", List.of("author3"), "publisher3", "2001", "imageUrl3",
            List.of(new IndustryIdentifier("ISBN_13", "978-3-56639-909-4")), 300, List.of("category3"),
            4.5f, 325, new ImageLinks("imageUrl3", "imageUrl2"), "fr"));

    BookSearchItem bookSearchItem1 = BookItemMapper.toBook(bookItem1);
    BookSearchItem bookSearchItem2 = BookItemMapper.toBook(bookItem2);
    BookSearchItem bookSearchItem3 = BookItemMapper.toBook(bookItem3);
    BookSearchResponse bookSearchResponse = new BookSearchResponse(
        3,
        List.of(bookItem1, bookItem2, bookItem3)
    );

    @BeforeEach
    void setUp() {
        restBookSearch = new RestBookSearch(restSearchEngine);
    }

    @Test
    @DisplayName("Book with no isbn13 should not appear in results")
    void searchBooks() {
        Mockito.when(restSearchEngine.search(any()))
            .thenReturn(bookSearchResponse);

        assertThat(restBookSearch.searchBooks("searchQuery"))
            .hasSize(2)
            .containsOnly(bookSearchItem1, bookSearchItem3);
    }

    @Test
    void getBook() {
        Mockito.when(restSearchEngine.getBookItem(any()))
            .thenReturn(bookItem1);

        assertThat(restBookSearch.getBook("bookId"))
            .isEqualTo(bookSearchItem1);
    }
}
