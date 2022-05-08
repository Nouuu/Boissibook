package org.esgi.boissibook.features.user.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponse(
        @Schema(description = "The user's id")
        @JsonProperty
        String userId,
        @Schema(description = "The user's email", example = "gregory@mail.com")
        @JsonProperty
        String email,
        @Schema(description = "The user's name", example = "Gregory")
        @JsonProperty
        String name) {
}
