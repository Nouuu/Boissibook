package org.esgi.boissibook.features.readlist.infra.config;

import org.esgi.boissibook.features.readlist.domain.BookReviewCommandHandler;
import org.esgi.boissibook.features.readlist.domain.BookReviewQueryHandler;
import org.esgi.boissibook.features.readlist.domain.BookReviewRepository;
import org.esgi.boissibook.features.readlist.infra.repository.JPABookReviewRepository;
import org.esgi.boissibook.features.readlist.infra.repository.SpringDataBookReviewRepository;
import org.esgi.boissibook.kernel.event.EventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBookReviewBeans {
    @Bean
    public BookReviewCommandHandler bookReviewCommandHandler(EventService eventService, BookReviewRepository bookReviewRepository) {
        return new BookReviewCommandHandler(bookReviewRepository, eventService);
    }

    @Bean
    public BookReviewQueryHandler bookReviewQueryHandler(BookReviewRepository bookReviewRepository) {
        return new BookReviewQueryHandler(bookReviewRepository);
    }

    @Bean
    BookReviewRepository bookReviewRepository(JPABookReviewRepository jpaBookReviewRepository) {
        return new SpringDataBookReviewRepository(jpaBookReviewRepository);
    }

}
