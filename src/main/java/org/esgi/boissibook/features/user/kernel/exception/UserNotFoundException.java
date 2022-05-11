package org.esgi.boissibook.features.user.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotfoundException;

public final class UserNotFoundException extends NotfoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
