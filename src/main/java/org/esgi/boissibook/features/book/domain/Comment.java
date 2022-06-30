package org.esgi.boissibook.features.book.domain;

import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.BookReviewId;
import org.esgi.boissibook.kernel.repository.UserId;

public class Comment {
    private String content;
    private BookReviewId reviewId;
    private UserId userId;
    private BookId bookId;

    public Comment(String content, BookReviewId reviewId, UserId userId, BookId bookId) {
        this.content = content;
        this.reviewId = reviewId;
        this.userId = userId;
        this.bookId = bookId;
    }

    public String comment() {
        return content;
    }

    public Comment comment(String comment) {
        this.content = comment;
        return this;
    }

    public BookReviewId reviewId() {
        return reviewId;
    }

    public Comment reviewId(BookReviewId reviewId) {
        this.reviewId = reviewId;
        return this;
    }

    public UserId userId() {
        return userId;
    }

    public Comment userId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public BookId bookId() {
        return bookId;
    }

    public Comment bookId(BookId bookId) {
        this.bookId = bookId;
        return this;
    }
}
