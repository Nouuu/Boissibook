package org.esgi.boissibook.features.readlist.kernel.exception;

public enum BookReviewExceptionMessage {
    REVIEW_NOT_FOUND("Review not found");
    private final String message;

    BookReviewExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
