package org.esgi.boissibook.features.book.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record BookIdResponse(
    @Schema(description = "The id of a book")
    @JsonProperty
    String id
) {
}
