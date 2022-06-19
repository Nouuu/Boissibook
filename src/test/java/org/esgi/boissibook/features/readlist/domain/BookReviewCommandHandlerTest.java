package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.features.readlist.infra.repository.InMemoryBookReviewRepository;
import org.esgi.boissibook.kernel.event.VoidEventService;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookReviewCommandHandlerTest {

    public BookReviewCommandHandler bookReviewCommandHandler;
    public BookReviewRepository bookReviewRepository;

    BookReview bookReview1;

    @BeforeEach
    void setUp() {
        bookReview1 = new BookReview(
            null,
            BookId.of("book1"),
            UserId.of("user1"),
            Visibility.PUBLIC,
            ReadingStatus.COMPLETED,
            320,
            3,
            "review1"
        );

        bookReviewRepository = new InMemoryBookReviewRepository();
        bookReviewCommandHandler = new BookReviewCommandHandler(bookReviewRepository, new VoidEventService());
    }

    @Test
    void createBookReview() {
        var bookReviewId = bookReviewCommandHandler.createReview(bookReview1);
        assertThat(bookReviewId).isNotNull();
        assertThat(bookReviewRepository.find(bookReviewId)).isNotNull()
            .isEqualTo(bookReview1.setBookReviewId(bookReviewId));
    }
}
