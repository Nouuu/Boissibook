package org.esgi.boissibook.features.readlist.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;

public record BookReviewResponse(
    @Schema(description = "The id of the readlist item", example = "7bd1b206-833d-4378-8064-05b162d80764")
    @JsonProperty
    String bookProgressionId,
    @Schema(description = "The id of the book", example = "7bd1b206-833d-4378-8064-05b162d80764")
    @JsonProperty
    String bookId,
    @Schema(description = "The id of the user", example = "7bd1b206-833d-4378-8064-05b162d80764")
    @JsonProperty
    String userId,
    @Schema(description = "The reading status of the book", example = "READING")
    @JsonProperty
    ReadingStatus readingStatus,
    @Schema(description = "The visibility of the review", example = "PUBLIC")
    @JsonProperty
    Visibility visibility,
    @Schema(description = "The number of the current page", example = "12")
    @JsonProperty
    int currentPage,
    @Schema(description = "The note given to the book", example = "5")
    @JsonProperty
    int note,
    @Schema(description = "The comment of the review", example = "This book is awesome")
    @JsonProperty
    String comment
) {
}
