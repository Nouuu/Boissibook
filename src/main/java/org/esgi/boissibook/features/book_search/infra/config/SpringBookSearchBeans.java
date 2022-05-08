package org.esgi.boissibook.features.book_search.infra.config;

import org.esgi.boissibook.features.book_search.domain.BookSearch;
import org.esgi.boissibook.features.book_search.domain.BookSearchQueryHandler;
import org.esgi.boissibook.features.book_search.infra.RestBookSearch;
import org.esgi.boissibook.features.book_search.infra.search_engine.GoogleBookRestSearchEngine;
import org.esgi.boissibook.features.book_search.infra.search_engine.RestSearchEngine;
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
