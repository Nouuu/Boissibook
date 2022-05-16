package org.esgi.boissibook.features.book_file.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotFoundException;

public class BookFileNotFoundException extends NotFoundException {
    public BookFileNotFoundException(String message) {
        super(message);
    }
}
