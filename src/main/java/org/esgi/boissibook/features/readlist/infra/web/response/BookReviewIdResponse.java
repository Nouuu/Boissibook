package org.esgi.boissibook.features.readlist.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record BookReviewIdResponse(
    @Schema(description = "The id of the readlist item", example = "7bd1b206-833d-4378-8064-05b162d80764")
    @JsonProperty
    String id
) {
}
