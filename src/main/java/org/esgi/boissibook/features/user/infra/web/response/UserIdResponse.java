package org.esgi.boissibook.features.user.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserIdResponse(
        @Schema(description = "The user's id")
        @JsonProperty
        String userId
) {
}
