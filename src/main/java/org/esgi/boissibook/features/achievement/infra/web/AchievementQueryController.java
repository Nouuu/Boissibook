package org.esgi.boissibook.features.achievement.infra.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esgi.boissibook.features.achievement.domain.AchievementQueryHandler;
import org.esgi.boissibook.features.achievement.infra.AchievementMapper;
import org.esgi.boissibook.features.achievement.infra.web.response.AchievementsResponse;
import org.esgi.boissibook.kernel.repository.UserId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Achievements controller", description = "Achievement features")
@RestController
@RequestMapping(value = "achievements", produces = MediaType.APPLICATION_JSON_VALUE)
public class AchievementQueryController {

    private final AchievementQueryHandler achievementQueryHandler;

    public AchievementQueryController(AchievementQueryHandler achievementQueryHandler) {
        this.achievementQueryHandler = achievementQueryHandler;
    }

    @Operation(summary = "Get user achievements", description = "Get all user's obtained achievements")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = AchievementsResponse.class)))
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<AchievementsResponse> getAllAchievements(@PathVariable("userId") String userId) {
        var achievements = achievementQueryHandler.getUserAchievements(UserId.of(userId)).stream().map(AchievementMapper::mapAchievementToAchievementResponse).toList();
        return ResponseEntity.ok(new AchievementsResponse(achievements));
    }
}
