package org.esgi.boissibook.features.book_file.kernel.exception;

import org.esgi.boissibook.kernel.exception.CompressionException;

public class ZipCompressionException extends CompressionException {
    public ZipCompressionException(String message) {
        super(message);
    }
}
