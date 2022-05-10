package org.esgi.boissibook.features.book_file.domain;

import java.io.IOException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class BookFileQueryHandler {
    private final BookFileRepository bookFileRepository;
    private final FileCompression fileCompression;

    public BookFileQueryHandler(BookFileRepository bookFileRepository, FileCompression fileCompression) {
        this.bookFileRepository = bookFileRepository;
        this.fileCompression = fileCompression;
    }

    public List<BookFile> getBookFiles(String bookId) {
        return bookFileRepository.findByBookId(bookId);
    }

    @Transactional
    public BookFile getBookFileById(String id) throws IOException {
        BookFile bookFile = bookFileRepository.find(id);
        bookFile.setDownloadCount(bookFile.downloadCount() + 1);
        bookFileRepository.save(bookFile);
        bookFile.setContent(fileCompression.decompress(bookFile.name(), bookFile.content()));
        return bookFile;
    }

    public long countBookFiles() {
        return bookFileRepository.count();
    }
}
