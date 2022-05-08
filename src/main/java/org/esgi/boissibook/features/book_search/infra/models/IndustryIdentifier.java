package org.esgi.boissibook.features.book_search.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IndustryIdentifier(
        @JsonProperty
        String type,
        @JsonProperty
        String identifier
) {
}
