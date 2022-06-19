package org.esgi.boissibook.features.readlist.infra.repository;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;
import org.esgi.boissibook.features.readlist.kernel.exception.BookReviewNotFoundException;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.kernel.repository.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryBookReviewRepositoryTest {

    private InMemoryBookReviewRepository bookReviewRepository;

    BookReview bookReview1;
    BookReview bookReview2;
    BookReview bookReview3;

    @BeforeEach
    void setUp() {
        bookReviewRepository = new InMemoryBookReviewRepository();

        bookReview1 = new BookReview(
            bookReviewRepository.nextId(),
            BookId.nextId(),
            UserId.nextId(),
            Visibility.PUBLIC,
            ReadingStatus.COMPLETED,
            32,
            5,
            "review1"
        );
        bookReview2 = new BookReview(
            bookReviewRepository.nextId(),
            BookId.nextId(),
            UserId.nextId(),
            Visibility.PUBLIC,
            ReadingStatus.COMPLETED,
            320,
            2,
            "review2"
        );
        bookReview3 = new BookReview(
            bookReviewRepository.nextId(),
            BookId.nextId(),
            UserId.nextId(),
            Visibility.PUBLIC,
            ReadingStatus.COMPLETED,
            68,
            3,
            "review3"
        );
    }

    @Test
    void nextId() {
        assertThat(bookReviewRepository.nextId())
            .isNotNull()
            .isInstanceOf(BookReviewId.class);
    }

    @Test
    void save() {
        bookReviewRepository.save(bookReview1);

        assertThat(bookReviewRepository.find(bookReview1.id()))
            .isEqualTo(bookReview1);
    }

    @Test
    void count() {
        assertThat(bookReviewRepository.count())
            .isZero();

        bookReviewRepository.save(bookReview1);

        assertThat(bookReviewRepository.count())
            .isEqualTo(1);

        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.count())
            .isEqualTo(3);
    }

    @Test
    void findAll() {
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.findAll())
            .hasSize(3)
            .containsOnly(bookReview1, bookReview2, bookReview3);
    }

    @Test
    void find() {
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.find(bookReview2.id()))
            .isEqualTo(bookReview2);
    }

    //find not found
    @Test
    void findNotFound() {
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        var bookReviewId = BookReviewId.nextId();

        assertThatThrownBy(() -> bookReviewRepository.find(bookReviewId))
            .isInstanceOf(BookReviewNotFoundException.class)
            .hasMessage("Review not found : " + bookReviewId);
    }

    @Test
    void delete() {
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);

        bookReviewRepository.delete(bookReview1);
        var bookReviewId = bookReview1.id();

        assertThatThrownBy(() -> bookReviewRepository.find(bookReviewId))
            .isInstanceOf(BookReviewNotFoundException.class)
            .hasMessage("Review not found : " + bookReviewId);

        assertThat(bookReviewRepository.count())
            .isEqualTo(1);
    }

    @Test
    void deleteAll() {
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        bookReviewRepository.deleteAll();

        assertThat(bookReviewRepository.count())
            .isZero();
    }

    @Test
    void findByBookIdAndUserId() {
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.findByBookIdAndUserId(bookReview2.getBookId(), bookReview2.getUserId()))
            .isEqualTo(bookReview2);
    }

    @Test
    void findByBookIdAndUserIdNotFound() {
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        var bookId = bookReview1.getBookId();
        var userId = bookReview2.getUserId();
        assertThatThrownBy(() -> bookReviewRepository.findByBookIdAndUserId(bookId, userId))
            .isInstanceOf(BookReviewNotFoundException.class)
            .hasMessage("Review not found : " + bookId + ", " + userId);
    }

    @Test
    void findByUserId() {
        bookReview2.setUserId(bookReview1.getUserId());
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.findByUserId(bookReview1.getUserId()))
            .hasSize(2)
            .containsOnly(bookReview1, bookReview2);
    }

    @Test
    void findByUserIdEmpty() {
        bookReviewRepository.save(bookReview1);
        bookReviewRepository.save(bookReview2);
        bookReviewRepository.save(bookReview3);

        assertThat(bookReviewRepository.findByUserId(UserId.nextId()))
            .isEmpty();
    }
}
