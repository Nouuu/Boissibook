package org.esgi.boissibook.features.book_file.infra.web.request;

public record BookFileSearchRequest(
    String bookId,
    String isbn
) {
}
