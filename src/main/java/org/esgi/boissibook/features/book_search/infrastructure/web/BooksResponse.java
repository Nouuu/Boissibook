package org.esgi.boissibook.features.book_search.infrastructure.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BooksResponse(
        @JsonProperty
        List<BookResponse> books
) {
}
