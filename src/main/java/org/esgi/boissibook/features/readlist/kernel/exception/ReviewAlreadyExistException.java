package org.esgi.boissibook.features.readlist.kernel.exception;

import org.esgi.boissibook.kernel.exception.ConflictException;

public class ReviewAlreadyExistException extends ConflictException {
    public ReviewAlreadyExistException(String message) {
        super(message);
    }
}
