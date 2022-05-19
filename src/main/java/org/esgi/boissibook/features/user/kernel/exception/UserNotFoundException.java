package org.esgi.boissibook.features.user.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotFoundException;

public final class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
