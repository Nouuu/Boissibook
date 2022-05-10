package org.esgi.boissibook.features.book_file.infra.web.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookFileResponse(
    @Schema(description = "Book file id")
    String id,
    @Schema(description = "Book file name")
    String name,
    @Schema(description = "Book file type")
    String type,
    @Schema(description = "Book id")
    String bookId,
    @Schema(description = "User who uploaded id")
    String userId,
    @Schema(description = "File download count")
    int downloadCount
) {
}
