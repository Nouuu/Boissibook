package org.esgi.boissibook.features.book.kernel.exception;

public enum BookExceptionMessage {
    BOOK_NOT_FOUND("Book not found"),
    BOOK_CONFLICT("Book already exists");

    private final String message;

    BookExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
