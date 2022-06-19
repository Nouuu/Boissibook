package org.esgi.boissibook.kernel.repository;

import java.util.Objects;
import java.util.UUID;

public record BookFileId(String value) implements DomainId<String> {
    public static BookFileId of(String bookFileId) {
        Objects.requireNonNull(bookFileId, "bookFileId cannot be null");
        return new BookFileId(bookFileId);
    }

    public static BookFileId nextId() {
        return new BookFileId(UUID.randomUUID().toString());
    }
}
