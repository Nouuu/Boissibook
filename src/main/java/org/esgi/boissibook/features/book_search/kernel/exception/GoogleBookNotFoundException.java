package org.esgi.boissibook.features.book_search.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotFoundException;

public class GoogleBookNotFoundException extends NotFoundException {

    public GoogleBookNotFoundException(String message) {
        super(message);
    }
}
