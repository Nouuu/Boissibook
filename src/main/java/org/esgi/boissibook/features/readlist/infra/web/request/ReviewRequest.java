package org.esgi.boissibook.features.readlist.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public record ReviewRequest(
    @Schema(description = "The note of the review", example = "3", required = true)
    @JsonProperty
    @Min(0)
    @Max(5)
    int note
) {
}
