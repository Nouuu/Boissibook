package org.esgi.boissibook.features.readlist.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotFoundException;

public final class BookReviewNotFoundException extends NotFoundException {
    public BookReviewNotFoundException(String message) {
        super(message);
    }
}
