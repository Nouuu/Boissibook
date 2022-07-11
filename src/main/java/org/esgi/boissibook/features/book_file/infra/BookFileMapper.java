package org.esgi.boissibook.features.book_file.infra;

import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.esgi.boissibook.features.book_file.infra.repository.BookFileEntity;
import org.esgi.boissibook.features.book_file.infra.web.response.BookFileResponse;
import org.esgi.boissibook.kernel.repository.BookFileId;
import org.esgi.boissibook.kernel.repository.BookId;
import org.esgi.boissibook.kernel.repository.UserId;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public final class BookFileMapper {

    private BookFileMapper() {
    }

    public static BookFile mapWebBookFileToBookFile(String bookId, String userId, MultipartFile webFile) throws IOException {
        return new BookFile(null, webFile.getOriginalFilename(), webFile.getContentType(), BookId.of(bookId), userId != null ? UserId.of(userId) : null, 0, webFile.getBytes());
    }

    public static BookFileResponse mapBookFileToBookFileResponse(BookFile bookFile) {
        return new BookFileResponse(bookFile.id().value(), bookFile.name(), bookFile.type(), bookFile.bookId().value(), bookFile.userId() != null ?bookFile.userId().value() : null, bookFile.downloadCount());
    }

    public static BookFile mapEntityBookFileToBookFile(BookFileEntity entity) {
        return new BookFile(BookFileId.of(entity.id()), entity.name(), entity.type(), BookId.of(entity.bookId()), entity.userId() != null ? UserId.of(entity.userId()) : null, entity.downloadCount(), entity.content());
    }

    public static BookFileEntity mapBookFileToBookFileEntity(BookFile bookFile) {
        return BookFileEntity.create(bookFile.id().value(), bookFile.name(), bookFile.type(), bookFile.bookId().value(), bookFile.userId() != null ?bookFile.userId().value() : null, bookFile.downloadCount(), bookFile.content());
    }
}
