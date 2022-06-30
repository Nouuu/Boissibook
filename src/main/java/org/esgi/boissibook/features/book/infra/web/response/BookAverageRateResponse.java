package org.esgi.boissibook.features.book.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record BookAverageRateResponse(
    @Schema(description = "Book's id")
    @JsonProperty
    String bookId,
    @Schema(description = "Book average rating")
    @JsonProperty
    double averageRate
) {
}
