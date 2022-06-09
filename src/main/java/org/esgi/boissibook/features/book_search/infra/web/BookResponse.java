package org.esgi.boissibook.features.book_search.infra.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BookResponse(
    @JsonProperty
    String id,
    @JsonProperty
    String title,
    @JsonProperty
    List<String> authors,
    @JsonProperty
    String publisher,
    @JsonProperty
    String publishedDate,
    @JsonProperty
    String description,
    @JsonProperty
    String isbn13,
    @JsonProperty
    String language,
    @JsonProperty
    String imgUrl,
    @JsonProperty
    int pages
) {
}
