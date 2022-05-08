package org.esgi.boissibook.features.user.kernel.exception;

public enum UserExceptionMessage {
    USER_NOT_FOUND("User not found");

    private final String message;

    UserExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
