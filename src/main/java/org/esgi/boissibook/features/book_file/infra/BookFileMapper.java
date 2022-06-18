package org.esgi.boissibook.features.book_file.infra;

import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.esgi.boissibook.features.book_file.infra.repository.BookFileEntity;
import org.esgi.boissibook.features.book_file.infra.web.response.BookFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public final class BookFileMapper {

    private BookFileMapper() {
    }

    public static BookFile mapWebBookFileToBookFile(String bookId, String userId, MultipartFile webFile) throws IOException {
        return new BookFile(null, webFile.getOriginalFilename(), webFile.getContentType(), bookId, userId, 0, webFile.getBytes());
    }

    public static BookFileResponse mapBookFileToBookFileResponse(BookFile bookFile) {
        return new BookFileResponse(bookFile.id(), bookFile.name(), bookFile.type(), bookFile.bookId(), bookFile.userId(), bookFile.downloadCount());
    }

    public static BookFile mapEntityBookFileToBookFile(BookFileEntity entity) {
        return new BookFile(entity.id(), entity.name(), entity.type(), entity.bookId(), entity.userId(), entity.downloadCount(), entity.content());
    }

    public static BookFileEntity mapBookFileToBookFileEntity(BookFile bookFile) {
        return BookFileEntity.create(bookFile.id(), bookFile.name(), bookFile.type(), bookFile.bookId(), bookFile.userId(), bookFile.downloadCount(), bookFile.content());
    }
}
