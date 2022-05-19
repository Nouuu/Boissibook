package org.esgi.boissibook.features.book_search.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class BookSearchQueryHandlerTest {

    @MockBean
    BookSearch bookSearch;

    BookSearchQueryHandler bookSearchQueryHandler;

    BookSearchItem bookSearchItem1 = new BookSearchItem("1", "title1", List.of("author1"), "publisher1", "2001", "description1", "isbn1", "fr1", "imgUrl1", 101);
    BookSearchItem bookSearchItem2 = new BookSearchItem("2", "title2", List.of("author2"), "publisher2", "2002", "description2", "isbn2", "fr2", "imgUrl2", 202);
    BookSearchItem bookSearchItem3 = new BookSearchItem("3", "title3", List.of("author3"), "publisher3", "2003", "description3", "isbn3", "fr3", "imgUrl3", 303);


    @BeforeEach
    void setUp() {
        bookSearchQueryHandler = new BookSearchQueryHandler(bookSearch);
    }

    @Test
    void searchBooks() {
        Mockito.when(bookSearch.searchBooks(any()))
                .thenReturn(List.of(bookSearchItem1, bookSearchItem2, bookSearchItem3));

        assertThat(bookSearchQueryHandler.searchBooks("searchQuery"))
                .hasSize(3)
                .containsOnly(bookSearchItem1, bookSearchItem2, bookSearchItem3);
    }

    @Test
    void getBook() {
        Mockito.when(bookSearch.getBook(any()))
                .thenReturn(bookSearchItem1);

        assertThat(bookSearchQueryHandler.getBook("id"))
                .isEqualTo(bookSearchItem1);
    }
}