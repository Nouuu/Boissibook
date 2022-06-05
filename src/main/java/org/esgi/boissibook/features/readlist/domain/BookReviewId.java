package org.esgi.boissibook.features.readlist.domain;

import java.util.Objects;

public record BookReviewId(String reviewId) {
    public static BookReviewId of(String reviewId) {
        Objects.requireNonNull(reviewId, "reviewId cannot be null");
        return new BookReviewId(reviewId);
    }
}
