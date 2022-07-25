package org.esgi.boissibook.features.achievement.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record AchievementsResponse(
    @Schema(description = "List of achievements")
    @JsonProperty
    List<AchievementResponse> achievements) {
}
