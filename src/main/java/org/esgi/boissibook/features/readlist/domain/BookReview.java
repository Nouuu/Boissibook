package org.esgi.boissibook.features.readlist.domain;

import org.esgi.boissibook.kernel.repository.BookReviewId;

import java.util.Objects;

public class BookReview {
    private BookReviewId bookReviewId;
    private String bookId;
    private String userId;
    private Visibility visibility;
    private ReadingStatus readingStatus;
    private int currentPage;
    private int note;
    private String comment;

    public BookReview(BookReviewId bookReviewId, String bookId, String userId, Visibility visibility, ReadingStatus readingStatus, int currentPage, int note, String comment) {
        this.bookReviewId = bookReviewId;
        this.bookId = Objects.requireNonNull(bookId);
        this.userId = Objects.requireNonNull(userId);
        this.visibility = Objects.requireNonNull(visibility);
        this.readingStatus = Objects.requireNonNull(readingStatus);
        this.currentPage = currentPage;
        this.note = note;
        this.comment = Objects.requireNonNull(comment);
    }

    public BookReviewId getBookReviewId() {
        return bookReviewId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public ReadingStatus getReadingStatus() {
        return readingStatus;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public BookReview setBookReviewId(BookReviewId bookReviewId) {
        this.bookReviewId = bookReviewId;
        return this;
    }

    public BookReview setBookId(String bookId) {
        this.bookId = bookId;
        return this;
    }

    public BookReview setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BookReview setVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public BookReview setReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
        return this;
    }

    public BookReview setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public BookReview setNote(int note) {
        this.note = note;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookReview that = (BookReview) o;

        if (currentPage != that.currentPage) return false;
        if (note != that.note) return false;
        if (!bookReviewId.equals(that.bookReviewId)) return false;
        if (!bookId.equals(that.bookId)) return false;
        if (!userId.equals(that.userId)) return false;
        if (visibility != that.visibility) return false;
        if (readingStatus != that.readingStatus) return false;
        return comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookReviewId, bookId, userId, visibility, readingStatus, currentPage, note, comment);
    }

    public BookReview setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
