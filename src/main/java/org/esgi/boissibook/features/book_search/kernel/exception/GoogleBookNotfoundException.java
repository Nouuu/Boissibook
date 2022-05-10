package org.esgi.boissibook.features.book_search.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotfoundException;

public class GoogleBookNotfoundException extends NotfoundException {

    public GoogleBookNotfoundException(String message) {
        super(message);
    }
}
