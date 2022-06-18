package org.esgi.boissibook.features.readlist.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotFoundBadRequestException;

public class UserNotFoundException extends NotFoundBadRequestException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
