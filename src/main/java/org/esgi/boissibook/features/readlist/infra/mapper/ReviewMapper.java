package org.esgi.boissibook.features.readlist.infra.mapper;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;
import org.esgi.boissibook.features.readlist.infra.repository.BookReviewEntity;
import org.esgi.boissibook.features.readlist.infra.web.request.CreateBookReviewRequest;
import org.esgi.boissibook.features.readlist.infra.web.request.UpdateBookReviewRequest;
import org.esgi.boissibook.features.readlist.infra.web.response.BookReviewResponse;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.kernel.repository.UserId;

public class ReviewMapper {
    public static BookReview toReview(CreateBookReviewRequest createBookProgressionRequest) {
        return new BookReview(
            null,
            BookId.of(createBookProgressionRequest.bookId()),
            UserId.of(createBookProgressionRequest.userId()),
            Visibility.valueOf(createBookProgressionRequest.visibility()),
            ReadingStatus.valueOf(createBookProgressionRequest.readingStatus()),
            createBookProgressionRequest.currentPage(),
            createBookProgressionRequest.note(),
            createBookProgressionRequest.comment()
        );
    }

    public static BookReview toReview(BookReviewId id, UpdateBookReviewRequest updateBookProgressionRequest) {
        return new BookReview(
            id,
            BookId.of(updateBookProgressionRequest.bookId()),
            UserId.of(updateBookProgressionRequest.userId()),
            Visibility.valueOf(updateBookProgressionRequest.visibility()),
            ReadingStatus.valueOf(updateBookProgressionRequest.readingStatus()),
            updateBookProgressionRequest.currentPage(),
            updateBookProgressionRequest.note(),
            updateBookProgressionRequest.comment()
        );
    }

    public static BookReviewEntity toEntity(BookReview bookReview) {
        return new BookReviewEntity(
            bookReview.id().value(),
            bookReview.getUserId().value(),
            bookReview.getBookId().value(),
            bookReview.getVisibility(),
            bookReview.getReadingStatus(),
            bookReview.getCurrentPage(),
            bookReview.getNote(),
            bookReview.getComment()
        );
    }

    public static BookReview fromEntity(BookReviewEntity bookReviewEntity) {
        return new BookReview(
            BookReviewId.of(bookReviewEntity.getBookReviewId()),
            BookId.of(bookReviewEntity.getBookId()),
            UserId.of(bookReviewEntity.getUserId()),
            bookReviewEntity.getVisibility(),
            bookReviewEntity.getReadingStatus(),
            bookReviewEntity.getCurrentPage(),
            bookReviewEntity.getNote(),
            bookReviewEntity.getComment()
        );
    }

    public static BookReviewResponse toResponse(BookReview bookReview) {
        return new BookReviewResponse(
            bookReview.id().value(),
            bookReview.getBookId().value(),
            bookReview.getUserId().value(),
            bookReview.getVisibility().name(),
            bookReview.getReadingStatus().name(),
            bookReview.getCurrentPage(),
            bookReview.getNote(),
            bookReview.getComment()
        );
    }
}
