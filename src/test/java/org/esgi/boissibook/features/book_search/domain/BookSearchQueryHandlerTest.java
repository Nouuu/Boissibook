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

    Book book1 = new Book("1", "title1", List.of("author1"), "publisher1", "2001", "description1", "isbn1", "fr1", "imgUrl1", 101);
    Book book2 = new Book("2", "title2", List.of("author2"), "publisher2", "2002", "description2", "isbn2", "fr2", "imgUrl2", 202);
    Book book3 = new Book("3", "title3", List.of("author3"), "publisher3", "2003", "description3", "isbn3", "fr3", "imgUrl3", 303);


    @BeforeEach
    void setUp() {
        bookSearchQueryHandler = new BookSearchQueryHandler(bookSearch);
    }

    @Test
    void searchBooks() {
        Mockito.when(bookSearch.searchBooks(any()))
                .thenReturn(List.of(book1, book2, book3));

        assertThat(bookSearchQueryHandler.searchBooks("searchQuery"))
                .hasSize(3)
                .containsOnly(book1, book2, book3);
    }

    @Test
    void getBook() {
        Mockito.when(bookSearch.getBook(any()))
                .thenReturn(book1);

        assertThat(bookSearchQueryHandler.getBook("id"))
                .isEqualTo(book1);
    }
}