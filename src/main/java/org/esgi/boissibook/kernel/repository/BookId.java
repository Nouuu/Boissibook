package org.esgi.boissibook.kernel.repository;

import java.util.Objects;
import java.util.UUID;

public record BookId(String value) implements DomainId<String> {
    public static BookId of(String bookId) {
        Objects.requireNonNull(bookId, "bookId cannot be null");
        return new BookId(bookId);
    }

    public static BookId nextId() {
        return new BookId(UUID.randomUUID().toString());
    }
}
