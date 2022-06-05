package org.esgi.boissibook.features.readlist.domain;

public record BookReviewId(String reviewId) {
    public static BookReviewId of(String reviewId) {
        return new BookReviewId(reviewId);
    }
}
