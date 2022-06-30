package org.esgi.boissibook.features.book.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
public record CommentResponse(
    @Schema(description = "Comment's review id")
    @JsonProperty
    String reviewId,
    @Schema(description = "Comment's book id")
    @JsonProperty
    String bookId,
    @Schema(description = "Comment's user id")
    @JsonProperty
    String userId,
    @Schema(description = "Comment's content")
    @JsonProperty
    String content
) {
}
