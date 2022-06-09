package org.esgi.boissibook.kernel.repository;

import java.util.Objects;
import java.util.UUID;

public record DefaultStringDomainId(String value) implements DomainId<String> {

    public static DefaultStringDomainId of(String id) {
        Objects.requireNonNull(id, "id cannot be null");
        return new DefaultStringDomainId(id);
    }

    public static DefaultStringDomainId nextId() {
        return new DefaultStringDomainId(UUID.randomUUID().toString());
    }
}
