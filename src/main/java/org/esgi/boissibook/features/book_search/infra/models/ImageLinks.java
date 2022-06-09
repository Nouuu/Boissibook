package org.esgi.boissibook.features.book_search.infra.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageLinks(
    @JsonProperty
    String smallThumbnail,
    @JsonProperty
    String thumbnail
) {
}
