package org.esgi.boissibook.features.readlist.infra.repository;


import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;

import javax.persistence.*;

@Entity
@Table(name = "book_review")
public class BookReviewEntity {
    @Id
    private String bookReviewId;
    private String bookId;
    private String userId;
    private Visibility visibility;
    private ReadingStatus readingStatus;
    private int currentPage;
    private int note;
    private String comment;

    public BookReviewEntity(String bookProgressionId, String bookId, String userId, Visibility visibility, ReadingStatus readingStatus, int currentPage, int note, String comment) {
        this.bookReviewId = bookProgressionId;
        this.bookId = bookId;
        this.userId = userId;
        this.visibility = visibility;
        this.readingStatus = readingStatus;
        this.currentPage = currentPage;
        this.note = note;
        this.comment = comment;
    }

    public BookReviewEntity() {

    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    @Enumerated(EnumType.STRING)
    public Visibility getVisibility() {
        return visibility;
    }

    @Enumerated(EnumType.STRING)
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

    public String getBookReviewId() {
        return bookReviewId;
    }
}
