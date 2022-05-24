package org.esgi.boissibook.features.readlist.domain;

public class BookReviewQueryHandler {
    private final BookReviewRepository bookReviewRepository;

    public BookReviewQueryHandler(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }
}
