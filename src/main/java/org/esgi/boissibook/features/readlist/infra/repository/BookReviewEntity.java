package org.esgi.boissibook.features.readlist.infra.repository;


import org.esgi.boissibook.features.book.infra.repository.BookEntity;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;
import org.esgi.boissibook.features.user.infra.repository.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "book_review")
public class BookReviewEntity {
    @Id
    private String bookReviewId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;
    private Visibility visibility;
    private ReadingStatus readingStatus;
    private int currentPage;
    private int note;
    private String comment;

    public BookReviewEntity() {

    }

    public BookReviewEntity(String bookReviewId, String userId, String bookId, Visibility visibility, ReadingStatus readingStatus, int currentPage, int note, String comment) {
        this.bookReviewId = bookReviewId;
        this.user = userId;
        this.book = bookId;
        this.visibility = visibility;
        this.readingStatus = readingStatus;
        this.currentPage = currentPage;
        this.note = note;
        this.comment = comment;
    }

    public UserEntity getUser() {
        return user;
    }

    public BookEntity getBook() {
        return book;
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
