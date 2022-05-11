package org.esgi.boissibook.features.book_search.domain;

import java.util.List;

public record Book(
        String id,
        String title,
        List<String> authors,
        String publisher,
        String publishedDate,
        String description,
        String isbn13,
        String language,
        String imgUrl,
        int pages
) {
}
