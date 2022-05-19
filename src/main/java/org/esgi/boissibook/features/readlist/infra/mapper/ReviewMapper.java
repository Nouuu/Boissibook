package org.esgi.boissibook.features.readlist.infra.mapper;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;
import org.esgi.boissibook.features.readlist.infra.repository.BookReviewEntity;
import org.esgi.boissibook.features.readlist.infra.web.request.CreateBookReviewRequest;
import org.esgi.boissibook.features.readlist.infra.web.request.UpdateBookReviewRequest;

public class ReviewMapper {
    public static BookReview toReview(CreateBookReviewRequest createBookProgressionRequest) {
        return new BookReview(
null,
            createBookProgressionRequest.bookId(),
            createBookProgressionRequest.userId(),
            Visibility.valueOf(createBookProgressionRequest.visibility()),
            ReadingStatus.valueOf(createBookProgressionRequest.readingStatus()),
            createBookProgressionRequest.currentPage(),
            createBookProgressionRequest.note(),
            createBookProgressionRequest.comment()
        );
    }
    public static BookReview toReview(UpdateBookReviewRequest updateBookProgressionRequest) {
        return new BookReview(
null,
            updateBookProgressionRequest.bookId(),
            updateBookProgressionRequest.userId(),
            Visibility.valueOf(updateBookProgressionRequest.visibility()),
            ReadingStatus.valueOf(updateBookProgressionRequest.readingStatus()),
            updateBookProgressionRequest.currentPage(),
            updateBookProgressionRequest.note(),
            updateBookProgressionRequest.comment()
        );
    }

    public static BookReviewEntity toEntity(BookReview bookReview) {
        return new BookReviewEntity(
            bookReview.getBookReviewId(),
            bookReview.getBookId(),
            bookReview.getUserId(),
            bookReview.getVisibility(),
            bookReview.getReadingStatus(),
            bookReview.getCurrentPage(),
            bookReview.getNote(),
            bookReview.getComment()
        );
    }
}
