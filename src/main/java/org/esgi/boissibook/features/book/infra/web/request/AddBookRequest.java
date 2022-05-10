package org.esgi.boissibook.features.book.infra.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AddBookRequest(
    @Schema(description = "The api id of the book to add", example = "8tAn3HYf898C", required = true)
    @NotNull
    @NotBlank
    String apiId
) {
}
