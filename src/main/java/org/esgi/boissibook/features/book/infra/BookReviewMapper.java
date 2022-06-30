package org.esgi.boissibook.features.book.infra;

import java.util.List;
import org.esgi.boissibook.features.book.domain.Book;
import org.esgi.boissibook.features.book.domain.Comment;
import org.esgi.boissibook.features.book.infra.repository.BookEntity;
import org.esgi.boissibook.features.book.infra.web.response.BookResponse;
import org.esgi.boissibook.features.book.infra.web.response.CommentResponse;
import org.esgi.boissibook.features.book.infra.web.response.CommentsResponse;
import org.esgi.boissibook.features.book_search.domain.BookSearchItem;
import org.esgi.boissibook.features.readlist.domain.BookReview;
import org.esgi.boissibook.kernel.repository.BookId;

public class BookReviewMapper {
    private BookReviewMapper() {
    }

    public static Comment mapBookReviewToComment(BookReview review) {
        return new Comment(review.getComment(), review.id(), review.getUserId(), review.getBookId());
    }

    public static CommentResponse mapCommentToCommentResponse(Comment comment) {
        return new CommentResponse(comment.reviewId().value(), comment.bookId().value(), comment.userId().value(), comment.comment());
    }

    public static CommentsResponse mapCommentListToCommentsResponse(List<Comment> comments) {
        return new CommentsResponse(comments.stream().map(BookReviewMapper::mapCommentToCommentResponse).toList());
    }

}
