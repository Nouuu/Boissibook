package org.esgi.boissibook.features.readlist.infra.web.request;

import org.esgi.boissibook.features.readlist.domain.ReadingStatus;
import org.esgi.boissibook.features.readlist.domain.Visibility;

public record CreateBookProgressionRequest(
    String bookProgressionId,
    String bookId,
    String userId,
    Visibility visibility,
    ReadingStatus readingStatus,
    int currentPage,
    int note,
    String comment
) {
}
