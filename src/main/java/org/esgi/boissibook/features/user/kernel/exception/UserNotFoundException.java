package org.esgi.boissibook.features.user.kernel.exception;

import org.webjars.NotFoundException;

public final class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
