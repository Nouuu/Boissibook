package org.esgi.boissibook.features.readlist.infra.repository;


import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_review")
public class BookReviewEntity {
    @Id
    private String bookReviewId;
    private String userId;
    private String bookId;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    @Enumerated(EnumType.STRING)
    private ReadingStatus readingStatus;
    private int currentPage;
    private int note;
    private String comment;

    public BookReviewEntity() {

    }

    public BookReviewEntity(String bookReviewId, String userId, String bookId, Visibility visibility, ReadingStatus readingStatus, int currentPage, int note, String comment) {
        this.bookReviewId = bookReviewId;
        this.userId = userId;
        this.bookId = bookId;
        this.visibility = visibility;
        this.readingStatus = readingStatus;
        this.currentPage = currentPage;
        this.note = note;
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
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

    public String getBookReviewId() {
        return bookReviewId;
    }
}
