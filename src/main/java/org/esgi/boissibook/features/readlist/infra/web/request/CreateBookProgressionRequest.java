package org.esgi.boissibook.features.readlist.infra.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;
import org.esgi.boissibook.infra.web.EnumValidator;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record CreateBookProgressionRequest(
        @Schema(description = "The book id", example = "7bd1b206-833d-4378-8064-05b162d80764", required = true)
        @JsonProperty
        @NotBlank
        @Size(min = 1, max = 255)
    String bookId,
        @Schema(description = "The user id", example = "7bd1b206-833d-4378-8064-05b162d80764", required = true)
        @JsonProperty
        @NotBlank
        @Size(min = 1, max = 255)
    String userId,
        @Schema(description = "The visibility of the progression", example = "PUBLIC", required = true)
        @JsonProperty
        @NotEmpty
        @EnumValidator(
            enumClazz = Visibility.class,
            message = "The visibility must be one of the following PRIVATE, PUBLIC"
        )
    String visibility,
        @Schema(description = "The reading status", example = "READING", required = true)
    ReadingStatus readingStatus,
    int currentPage,
    int note,
    String comment
) {
}
