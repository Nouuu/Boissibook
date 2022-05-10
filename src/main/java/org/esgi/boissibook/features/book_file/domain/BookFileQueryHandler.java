package org.esgi.boissibook.features.book_file.domain;

import java.util.List;

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

    public BookFile getBookFileById(String id) {
        BookFile bookFile = bookFileRepository.find(id);
        bookFile.setDownloadCount(bookFile.downloadCount() + 1);
        bookFileRepository.save(bookFile);
        bookFile.setContent(fileCompression.decompress(bookFile.name(), bookFile.content()));
        return bookFile;
    }

    public long countBookFiles(String bookId) {
        return bookFileRepository.countAllByBookId(bookId);
    }
}
