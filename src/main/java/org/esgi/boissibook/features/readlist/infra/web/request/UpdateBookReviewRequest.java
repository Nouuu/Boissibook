package org.esgi.boissibook.features.readlist.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;

public record UpdateBookReviewRequest(
    @Schema(description = "The book id", example = "7bd1b206-833d-4378-8064-05b162d80764", required = true)
    @JsonProperty
    @NotBlank
    @Size(min = 1, max = 255)
    String bookId,
    @Schema(description = "The user id", example = "7bd1b206-833d-4378-8064-05b162d80764", required = true)
    @JsonProperty
    @NotBlank
    @Size(min = 1, max = 255)
    String userId,
    @Schema(
        description = "The visibility of the progression",
        allowableValues = {"PUBLIC", "PRIVATE"},
        example = "PUBLIC",
        required = true
    )
    @JsonProperty
    @NotEmpty
    String visibility,
    @Schema(
        description = "The reading status",
        allowableValues = {"PLAN_TO_READ", "READING", "COMPLETED", "ON_HOLD", "DROPPED"},
        example = "READING",
        required = true
    )
    @JsonProperty
    @NotEmpty
    String readingStatus,
    @Schema(description = "The progression in number of page", example = "50", required = true)
    @JsonProperty
    @Min(0)
    int currentPage,
    @Schema(description = "The note of the review", example = "3", required = true)
    @JsonProperty
    @Min(0)
    @Max(5)
    int note,
    @Schema(description = "The comment of the review", example = "I'm a good reader", required = true)
    @JsonProperty
    @NotBlank
    @Size(min = 1, max = 255)
    String comment
) {
}
