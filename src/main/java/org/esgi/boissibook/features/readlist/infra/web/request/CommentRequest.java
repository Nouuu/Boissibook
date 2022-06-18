package org.esgi.boissibook.features.readlist.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CommentRequest(
    @Schema(description = "The comment of the review", example = "I'm a good reader", required = true)
    @JsonProperty
    @NotBlank
    @Size(min = 1, max = 255)
    String comment
) {
}
