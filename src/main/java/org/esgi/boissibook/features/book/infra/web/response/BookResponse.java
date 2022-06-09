package org.esgi.boissibook.features.book.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record BookResponse(
    @Schema(description = "The book's id")
    @JsonProperty
    String id,
    @Schema(description = "The book's api id")
    @JsonProperty
    String apiId,
    @Schema(description = "The book's title")
    @JsonProperty
    String title,
    @Schema(description = "The book's authors")
    @JsonProperty
    List<String> authors,
    @Schema(description = "The book's publisher")
    @JsonProperty
    String publisher,
    @Schema(description = "The book's publication date")
    @JsonProperty
    String publishedDate,
    @Schema(description = "The book's description")
    @JsonProperty
    String description,
    @Schema(description = "The book's isbn")
    @JsonProperty
    String isbn13,
    @Schema(description = "The book's language")
    @JsonProperty
    String language,
    @Schema(description = "The book's image link")
    @JsonProperty
    String imgUrl,
    @Schema(description = "The book's number of pages")
    @JsonProperty
    int pages
) {
}
