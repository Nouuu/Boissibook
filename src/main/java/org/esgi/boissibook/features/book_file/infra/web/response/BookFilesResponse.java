package org.esgi.boissibook.features.book_file.infra.web.response;

import java.util.List;

public record BookFilesResponse(List<BookFileResponse> bookFiles) {
}
