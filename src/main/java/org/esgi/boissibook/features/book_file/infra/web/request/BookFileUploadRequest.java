package org.esgi.boissibook.features.book_file.infra.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record BookFileUploadRequest(
    @Schema(description = "The file to upload", required = true)
    @NotNull
    MultipartFile file,
    @Schema(description = "The book's id", required = true)
    @NotBlank
    @NotNull
    String bookId,
    @Schema(description = "The user's id")
    String userId
) {
}
