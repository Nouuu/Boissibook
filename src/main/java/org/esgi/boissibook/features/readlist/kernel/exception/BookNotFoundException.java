package org.esgi.boissibook.features.readlist.kernel.exception;


import org.esgi.boissibook.kernel.exception.NotFoundBadRequestException;

public final class BookNotFoundException extends NotFoundBadRequestException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
