package org.esgi.boissibook.features.book_file.kernel.exception;

public enum BookFileExceptionMessage {
    BOOK_FILE_NOT_FOUND("Book file not found");

    private final String message;

    BookFileExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
