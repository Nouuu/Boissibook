package org.esgi.boissibook.features.bookfile.infra.config;

import org.esgi.boissibook.features.bookfile.domain.BookFileCommandHandler;
import org.esgi.boissibook.features.bookfile.domain.BookFileQueryHandler;
import org.esgi.boissibook.features.bookfile.domain.BookFileRepository;
import org.esgi.boissibook.features.bookfile.domain.FileCompression;
import org.esgi.boissibook.features.bookfile.infra.repository.JPABookFileRepository;
import org.esgi.boissibook.features.bookfile.infra.repository.SpringDataBookFileRepository;
import org.esgi.boissibook.features.bookfile.infra.ZipFileCompression;
import org.esgi.boissibook.kernel.event.EventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
    public BookFileCommandHandler bookFileCommandHandler(BookFileRepository bookFileRepository, EventService eventService, FileCompression fileCompression) {
        return new BookFileCommandHandler(bookFileRepository, eventService, fileCompression);
    }

    @Bean
    public BookFileQueryHandler bookFileQueryHandler(BookFileRepository bookFileRepository, FileCompression fileCompression) {
        return new BookFileQueryHandler(bookFileRepository, fileCompression);
    }
}
