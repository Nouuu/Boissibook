package org.esgi.boissibook.features.book.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record BooksResponse(
    @Schema(description = "List of books")
    @JsonProperty
    List<BookResponse> books
) {
}
