package org.esgi.boissibook.features.book_file.infra.config;

import org.esgi.boissibook.features.book.domain.BookRepository;
import org.esgi.boissibook.features.book_file.domain.BookFileCommandHandler;
import org.esgi.boissibook.features.book_file.domain.BookFileQueryHandler;
import org.esgi.boissibook.features.book_file.domain.BookFileRepository;
import org.esgi.boissibook.features.book_file.domain.BookFileSearch;
import org.esgi.boissibook.features.book_file.domain.FileCompression;
import org.esgi.boissibook.features.book_file.infra.ScrapperBookFileSearch;
import org.esgi.boissibook.features.book_file.infra.ZipFileCompression;
import org.esgi.boissibook.features.book_file.infra.repository.JPABookFileRepository;
import org.esgi.boissibook.features.book_file.infra.repository.SpringDataBookFileRepository;
import org.esgi.boissibook.kernel.event.EventService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
public class SpringBookFileBeans {

    @Bean
    BookFileRepository bookFileRepository(JPABookFileRepository jpaBookFileRepository) {
        return new SpringDataBookFileRepository(jpaBookFileRepository);
    }

    @Bean
    FileCompression fileCompression() {
        return new ZipFileCompression();
    }

    @Bean
    public BookFileCommandHandler bookFileCommandHandler(
        BookFileRepository bookFileRepository,
        BookRepository bookRepository,
        EventService eventService,
        FileCompression fileCompression,
        BookFileSearch bookFileSearch
    ) {
        return new BookFileCommandHandler(bookFileRepository, bookRepository, eventService, fileCompression, bookFileSearch);
    }

    @Bean
    public BookFileQueryHandler bookFileQueryHandler(BookFileRepository bookFileRepository, FileCompression fileCompression) {
        return new BookFileQueryHandler(bookFileRepository, fileCompression);
    }

    @Bean
    public BookFileSearch bookFileSearch() {
        return new ScrapperBookFileSearch(scrapperConfigurationProperties().getScrapperApiUrl());
    }

    @Bean
    @ConfigurationProperties(prefix = "scrapper")
    public ScrapperConfigurationProperties scrapperConfigurationProperties() {
        return new ScrapperConfigurationProperties();
    }
}
