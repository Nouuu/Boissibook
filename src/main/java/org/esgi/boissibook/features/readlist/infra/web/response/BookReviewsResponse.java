package org.esgi.boissibook.features.readlist.infra.web.response;


import java.util.List;

public record BookReviewsResponse(List<BookReviewResponse> reviews) {
}
