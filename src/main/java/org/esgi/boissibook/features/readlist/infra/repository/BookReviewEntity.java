package org.esgi.boissibook.features.readlist.infra.repository;


import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_progression")
public class BookReviewEntity {
    @Id
    private String bookProgressionId;
    private String bookId;
    private String userId;
    private Visibility visibility;
    private ReadingStatus readingStatus;
    private int currentPage;
    private int note;
    private String comment;

    public BookReviewEntity(String bookProgressionId, String bookId, String userId, Visibility visibility, ReadingStatus readingStatus, int currentPage, int note, String comment) {
        this.bookProgressionId = bookProgressionId;
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

    public String getBookProgressionId() {
        return bookProgressionId;
    }
}
