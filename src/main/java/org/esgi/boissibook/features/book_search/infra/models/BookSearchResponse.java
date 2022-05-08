package org.esgi.boissibook.features.book_search.infra.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BookSearchResponse(
        @JsonProperty
        int totalItems,
        @JsonProperty
        List<BookItem> items) {
}
