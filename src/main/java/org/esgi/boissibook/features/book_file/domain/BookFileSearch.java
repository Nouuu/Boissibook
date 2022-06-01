package org.esgi.boissibook.features.book_file.domain;

import org.esgi.boissibook.features.book.domain.Book;

public interface BookFileSearch {
    BookFileSearchStatus searchBookFile(Book book);
}
