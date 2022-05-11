package org.esgi.boissibook.features.readlist.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;

public record ProgressRequest(
    @Schema(description = "The progression in number of page", example = "50", required = true)
    @JsonProperty
    @Min(0)
    int currentPage
) {
}
