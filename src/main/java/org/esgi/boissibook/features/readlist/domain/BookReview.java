package org.esgi.boissibook.features.readlist.domain;

public class BookReview {
    private String bookReviewId;
    private String bookId;
    private String userId;
    private Visibility visibility;
    private ReadingStatus readingStatus;
    private int currentPage;
    private int note;
    private String comment;

    public BookReview(String bookReviewId, String bookId, String userId, Visibility visibility, ReadingStatus readingStatus, int currentPage, int note, String comment) {
        this.bookReviewId = bookReviewId;
        this.bookId = bookId;
        this.userId = userId;
        this.visibility = visibility;
        this.readingStatus = readingStatus;
        this.currentPage = currentPage;
        this.note = note;
        this.comment = comment;
    }

    public String getBookReviewId() {
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
    public void setBookReviewId(String bookReviewId) {
        this.bookReviewId = bookReviewId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void setReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
