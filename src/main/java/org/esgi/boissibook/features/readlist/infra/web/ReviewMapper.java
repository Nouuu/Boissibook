package org.esgi.boissibook.features.readlist.infra.web;

import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;
import org.esgi.boissibook.features.readlist.infra.web.request.CreateBookReviewRequest;

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
}
