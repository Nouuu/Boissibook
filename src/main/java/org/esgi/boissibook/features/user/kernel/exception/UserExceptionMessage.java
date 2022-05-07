package org.esgi.boissibook.features.user.kernel.exception;

public enum UserExceptionMessage {
    USER_NOT_FOUND("User not found"),
    ;

    private final String s;

    UserExceptionMessage(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
