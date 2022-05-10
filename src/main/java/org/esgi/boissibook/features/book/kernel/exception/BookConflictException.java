package org.esgi.boissibook.features.book.kernel.exception;

import org.esgi.boissibook.kernel.exception.ConflictException;

public class BookConflictException extends ConflictException {
    public BookConflictException(String message) {
        super(message);
    }
}
