package org.esgi.boissibook.infra.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;

public record UnhandledExceptionResponse(
    @Schema(description = "Where the error occurred")
    @JsonProperty
    String source,
    @Schema(description = "The error occurred date")
    @JsonProperty
    ZonedDateTime occurredDate,
    @Schema(description = "The error message")
    @JsonProperty
    String message
) {
}
