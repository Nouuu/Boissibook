package org.esgi.boissibook.features.book_search.infrastructure.config;

import org.esgi.boissibook.features.book_search.domain.BookSearch;
import org.esgi.boissibook.features.book_search.domain.BookSearchQueryHandler;
import org.esgi.boissibook.features.book_search.infrastructure.RestBookSearch;
import org.esgi.boissibook.features.book_search.infrastructure.search_engine.GoogleBookRestSearchEngine;
import org.esgi.boissibook.features.book_search.infrastructure.search_engine.RestSearchEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBookSearchBeans {

    @Bean
    public BookSearchQueryHandler bookSearchQueryHandler(BookSearch bookSearch) {
        return new BookSearchQueryHandler(bookSearch);
    }

    @Bean
    public BookSearch bookSearch(RestSearchEngine restSearchEngine) {
        return new RestBookSearch(restSearchEngine);
    }

    @Bean
    public RestSearchEngine restSearchEngine() {
        return new GoogleBookRestSearchEngine();
    }
}
