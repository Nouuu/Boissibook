package org.esgi.boissibook.features.readlist.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

public record StatusRequest(
    @Schema(description = "The reading status", example = "READING", required = true)
    @JsonProperty
    @NotEmpty
    String status
) {
}
