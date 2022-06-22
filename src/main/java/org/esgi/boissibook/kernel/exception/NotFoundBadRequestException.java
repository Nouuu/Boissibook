package org.esgi.boissibook.kernel.exception;

public class NotFoundBadRequestException extends RuntimeException {
    public NotFoundBadRequestException(String message) {
        super(message);
    }
}
