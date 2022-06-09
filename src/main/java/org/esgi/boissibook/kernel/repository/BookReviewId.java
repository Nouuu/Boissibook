package org.esgi.boissibook.kernel.repository;

import java.util.Objects;
import java.util.UUID;

public record BookReviewId(String value) implements DomainId<String> {
    public static BookReviewId of(String reviewId) {
        Objects.requireNonNull(reviewId, "reviewId cannot be null");
        return new BookReviewId(reviewId);
    }

    public static BookReviewId nextId() {
        return new BookReviewId(UUID.randomUUID().toString());
    }
}
