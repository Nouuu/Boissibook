package org.esgi.boissibook.features.book.infra.web.response;

import java.util.List;

public record CommentsResponse(List<CommentResponse> comments) {
}
