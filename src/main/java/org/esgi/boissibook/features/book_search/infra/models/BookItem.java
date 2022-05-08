package org.esgi.boissibook.features.book_search.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookItem(
        @JsonProperty
        String id,
        @JsonProperty
        String selfLink,
        @JsonProperty
        VolumeInfo volumeInfo
) {
}
