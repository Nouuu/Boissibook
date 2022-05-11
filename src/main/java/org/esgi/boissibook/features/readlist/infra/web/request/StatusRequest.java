package org.esgi.boissibook.features.readlist.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.infra.validation.EnumValidator;

import javax.validation.constraints.NotEmpty;

public record StatusRequest(
    @Schema(description = "The reading status", example = "READING", required = true)
    @JsonProperty
    @NotEmpty
    @EnumValidator(
        enumClazz = ReadingStatus.class,
        message = "The reading status must be one of the following PLAN_TO_READ, READING, COMPLETED, ON_HOLD, DROPPED"
    )
    String status
) {
}
