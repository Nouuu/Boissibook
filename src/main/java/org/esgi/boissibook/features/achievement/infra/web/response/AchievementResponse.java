package org.esgi.boissibook.features.achievement.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import org.esgi.boissibook.features.achievement.domain.AchievementLevel;

public record AchievementResponse(
    @Schema(description = "The achievement id")
    @JsonProperty
    String id,
    @Schema(description = "User's id")
    @JsonProperty
    String userId,
    @Schema(description = "The achievement title")
    @JsonProperty
    String title,
    @Schema(description = "The achievement description")
    @JsonProperty
    String description,
    @Schema(description = "The date when it was achieved")
    @JsonProperty
    ZonedDateTime achievedAt,
    @Schema(description = "The level achieved")
    @JsonProperty
    AchievementLevel level
) {
}
