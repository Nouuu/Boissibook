package org.esgi.boissibook.features.book_search.infra.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record VolumeInfo(
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
    List<IndustryIdentifier> industryIdentifiers,
    @JsonProperty
    int pageCount,
    @JsonProperty
    List<String> categories,
    @JsonProperty
    float averageRating,
    @JsonProperty
    int ratingsCount,
    @JsonProperty
    ImageLinks imageLinks,
    @JsonProperty
    String language
) {
}
