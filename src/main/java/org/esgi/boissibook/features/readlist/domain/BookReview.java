package org.esgi.boissibook.features.readlist.domain;

public class BookReview {
    private final String bookProgressionId;
    private final String bookId;
    private final String userId;
    private final Visibility visibility;
    private final ReadingStatus readingStatus;
    private final int currentPage;
    private final int note;
    private final String comment;

    public BookReview(String bookProgressionId, String bookId, String userId, Visibility visibility, ReadingStatus readingStatus, int currentPage, int note, String comment) {
        this.bookProgressionId = bookProgressionId;
        this.bookId = bookId;
        this.userId = userId;
        this.visibility = visibility;
        this.readingStatus = readingStatus;
        this.currentPage = currentPage;
        this.note = note;
        this.comment = comment;
    }

    public String getBookProgressionId() {
        return bookProgressionId;
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
}
