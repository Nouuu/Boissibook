package org.esgi.boissibook.features.book.infra.config;

import org.esgi.boissibook.features.book.domain.BookCommandHandler;
import org.esgi.boissibook.features.book.domain.BookQueryHandler;
import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.book.infra.repository.JPABookRepository;
import org.esgi.boissibook.features.book.infra.repository.SpringDataBookRepository;
import org.esgi.boissibook.kernel.event.EventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBookBeans {
    @Bean
    public BookCommandHandler bookCommandHandler(BookRepository bookRepository, EventService eventService) {
        return new BookCommandHandler(bookRepository, eventService);
    }

    @Bean
    public BookQueryHandler bookQueryHandler(BookRepository bookRepository) {
        return new BookQueryHandler(bookRepository);
    }

    @Bean
    BookRepository bookRepository(JPABookRepository jpaBookRepository) {
        return new SpringDataBookRepository(jpaBookRepository);
    }
}
